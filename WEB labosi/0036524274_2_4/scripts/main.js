function Ukupno(){
    let Ukupno=0;
    let mapa = new Map(JSON.parse(localStorage.kosarica));
    for(let number of mapa.values()){
        Ukupno+=number;
    }
    document.getElementById("cart-items").textContent = Ukupno;
}

window.onload=Ukupno;