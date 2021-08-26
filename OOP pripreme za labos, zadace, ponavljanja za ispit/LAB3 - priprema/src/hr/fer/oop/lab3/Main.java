package hr.fer.oop.lab3;

public class Main {

	public static void main(String[] args) {

		FootballPlayer defaultPlayer = new FootballPlayer();

		if (defaultPlayer.equals(null)) {
			System.err.println("TEST FAILED: Igrac ne bi smio biti jednak null.");
		}
		
		FootballPlayer unemotional = new FootballPlayer("Player", "Croatia", -10, 110,
					PlayingPosition.FW);
		if(unemotional.getEmotion() < 0 || unemotional.getPlayingSkill() > 100){
			System.err.println("TEST FAILED: Emocija mora biti unutar raspona!");
		}
		
		FootballPlayer emotionalPlayer = new FootballPlayer("Player", "Croatia", 110, -10,
					PlayingPosition.GK);
		if(emotionalPlayer.getEmotion() > 100 || emotionalPlayer.getPlayingSkill() < 0){
			System.err.println("TEST FAILED: Emocija mora biti unutar raspona!!");
		}
		
		FootballPlayer waterBoy = new FootballPlayer("Player", "Croatia", 100, 100, null);
		if(waterBoy.getPlayingPosition() == null){
			System.err.println("TEST FAILED: Vrijednosti igraca moraju biti u rasponu!");
		}


		System.out.println("Ako nemate 'TEST FAILED' poruke onda je ovaj osnovni test prosao.\nSada napisite neke svoje testove kako biste isprobali druge funkcionalnosti.");

	}


}
