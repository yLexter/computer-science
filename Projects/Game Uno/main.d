import std.stdio;
import std.container;

import entidades.Carta;
import entidades.Uno;
import entidades.Baralho;
import entidades.Jogador;
import entidades.RegrasUno;

void main(){

    RegrasUno regrasUno = new RegrasUno();
    Uno uno = new Uno(regrasUno);

    regrasUno.setUno(uno);

    uno.main();
}
