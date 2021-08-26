package auditorne3.drugizad;

public class GlassOfWater {
		private int GlassSize, currentWaterAmount, currentIceCubeCount;
		
		public GlassOfWater(int GlassSize) {
			this.GlassSize=GlassSize;
		}
		
		public void addWater(int waterAmount) {
			System.out.println("Glass contains:" + currentWaterAmount + "ml of water and " + currentIceCubeCount + " ice cubes before addition.");
			System.out.println("Adding " + waterAmount + "ml of water.");
			currentWaterAmount+=waterAmount;
			checkForOverflow();
			System.out.println("Glass contains:" + currentWaterAmount + "ml of water and " + currentIceCubeCount + " ice cubes after addition.");
		}
		
		public void addIceCubes(int numberOfIceCubes) {
			System.out.println("Glass contains:" + currentWaterAmount + "ml of water and " + currentIceCubeCount + " ice cubes before addition.");
			System.out.println("Adding " + numberOfIceCubes + " ice cubes.");
			currentIceCubeCount+=numberOfIceCubes;
			checkForOverflow();
			System.out.println("Glass contains:" + currentWaterAmount + "ml of water and " + currentIceCubeCount + " ice cubes after addition.");
		}
		
		private void checkForOverflow() {
			
			int currentIceCubeVolume = currentIceCubeCount*50;
			
			if((currentWaterAmount + currentIceCubeVolume)>GlassSize) {
				
				int overFlowVolume = (currentWaterAmount + currentIceCubeVolume) - GlassSize;
				
				if(overFlowVolume<=currentWaterAmount) {
					currentWaterAmount-=overFlowVolume;
					System.out.println(overFlowVolume + "ml of water has overflown.");
					
				}else {
					int overFlownCubes = 0;
					currentWaterAmount=0;
					overFlowVolume=currentIceCubeVolume-GlassSize;
					while(overFlowVolume>0) {
						overFlowVolume-=50;
						--currentIceCubeCount;
						overFlownCubes++;
					}	
					System.out.println("All water has overflown. " + overFlownCubes + " ice cubes have overflown.");
				}	
			}	
		}
		
		
		public static void main(String[] args) {
			GlassOfWater glass = new GlassOfWater(250);
			glass.addWater(200);
			glass.addIceCubes(3);
			glass.addIceCubes(4);
			}
		
}
