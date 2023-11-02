package main;

public class ListaDuplamenteEncadeada  {

    public Node cabeca;

    public Node cauda;
    
    public void adicionarNoInicio(int element) {

        Node node = new Node(element);

        if (cabeca == null) {
            cabeca = node;
            cauda = node;
        } else {
            cabeca.anterior = node;
            node.proximo = cabeca;
            cabeca = node;
        }
    }

    public void adicionarNoFinal(int element) {

        Node node = new Node(element);

        if (cabeca == null) {
            cabeca = node;
            cauda = node;
        } else {
            cauda.proximo = node;
            node.anterior = cauda;
            cauda = node;
        }

    }
    
    public boolean buscar(Integer valor) {
        Node current = cabeca;

         while(current != null) {
        	 
        	 if (current.informacao == valor) {
        		 return true;
        	 }
        	 
             current = current.proximo;
         }

         return false;
    }
    
    public void adicionarNaPosicaoK(int valor, int posicao) {
    	
    	Node atual = cabeca, no = new Node(valor), aux = null;
    	int contador = 0;
    	
    	if (posicao == 0) {
    		adicionarNoInicio(valor);
    		return;
    	}
    	
    	while (atual != null && contador < posicao) {    		
    		atual = atual.proximo;
    		contador++;
    	}
    	
    	if (atual == null && posicao >= contador) {
    		adicionarNoFinal(valor);
    		return;
    	}
    	
    	atual = atual.anterior;
		aux = atual.proximo;
		
		atual.proximo = no;
		no.anterior = atual;
		no.proximo = aux;
		aux.anterior = no;
	
    }
    
    public void remover(int valor) {

        Node atual = cabeca, aux = null;

         if (cabeca == null)
             return;

         if (cabeca.informacao == valor) {
             
             if (cabeca.proximo != null) {
            	 cabeca = cabeca.proximo;
            	 cabeca.anterior = null;
             } else {
            	 cabeca = null;
            	 cauda = null;
             }
             
             
            	 
             return;
         }

         while (atual != null) {
        	 
             if (atual.informacao == valor) {
            	 
            	 if (atual.equals(cauda)) {
            		cauda = cauda.anterior;
            		cauda.proximo = null;
            	 } 
            	 
            	aux = atual.anterior;
            	aux.proximo = atual.proximo;
           
            
             }
             
             atual = atual.proximo;
         }
         
        
     }
        
   
}