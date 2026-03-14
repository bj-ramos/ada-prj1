public class dellanoy {
  


  /**
   * a e b são as coordenados em que o jogadorn se encontra
   * 
   * O jogador pode se mover para cima, para direita ou na diagonal,
   * portanto, o return é a soma de:
   * dellanoy(a-1,b)(mover para a direita)
   * dellanoy(a-1,b-1)(mover na diagonal)
   * dellanoy(a,b-1)mover para cima)
   * 
   * 
   * 
   */
  public static int dellanoy(int a, int b){
    if(a==0|| b == 0) return 1; //isto é caso o jogador se encontre em uma das bermas do tabuleiro, 
    // portanto o único movimento que poderá executar é só para cima ou só para baixo
    //o problema caga para o caso a e b sejam 0(ou seja está no destino, "uga buga mete 1 tbm wtv")
    return dellanoy(a-1,b) + dellanoy(a-1,b-1) + dellanoy(a,b-1);



  }
}
