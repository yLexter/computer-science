package main;

public class listaSimplesmenteEncadeada  {

    public CustomNode cabeca;

    public CustomNode cauda;

    // Classe Node
    public static class CustomNode {

        public CustomNode next;

        public int valor;

        public CustomNode(Integer valor) {
            this.valor = valor;
            this.next = null;
        }
    }


    public void adicionarNoInicio(Integer valor) {

        CustomNode node = new CustomNode(valor);

        if (cabeca == null) {
            cabeca = node;
            cauda = node;
        } else {
            node.next = cabeca;
            cabeca = node;
        }
    }

    public void adicionarNoFinal(Integer valor) {
    	
    	CustomNode node = new CustomNode(valor);
    	
    	if (cabeca == null) {
    		cabeca = node;
    		cauda = node;
    	} else {
    		cauda.next = node; 
    		cauda = node;
    	}
    	
    }

    
    public boolean buscar(Integer valor) {
        CustomNode current = cabeca;

         while(current != null) {
        	 
        	 if (current.valor == valor) {
        		 return true;
        	 }
        	 
             current = current.next;
         }

         return false;
    }



}   