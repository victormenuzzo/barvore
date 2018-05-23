package utfpr.dainf.ct.ed.exemplo;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * UTFPR - Universidade Tecnológica Federal do Paraná
 * DAINF - Departamento Acadêmico de Informática
 * 
 * Exemplo de implementação de árvore AVL.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 * @param <K> O tipo de dado da chave.
 */
public class ArvoreB<K extends Comparable<? super K>> {
    protected int grauMin;
    protected NoArvoreB<K> raiz;

    /**
     * Cria uma árvore AVL com grau mínimo especificado.
     * Lança uma exceção caso ao grau especificado seja menor do que 2.
     * @param grauMin O grau mínimo
     * @throws RuntimeException Caso t < 2
     */
    public ArvoreB(int grauMin) {
        if (grauMin < 2) {
            throw new RuntimeException("Grau mínimo deve ser >= 2");
        }
        this.grauMin = grauMin;
        raiz = alocaNo();
    }

    /**
     * Retorna o grau mínimo da árvore.
     * @return O grau mínimo da árvore
     */
    public int getGrauMin() {
        return grauMin;
    }

    /**
     * Retorna a raiz da árvore.
     * @return A raiz da árvore
     */
    public NoArvoreB<K> getRaiz() {
        return raiz;
    }
    
    /**
     * Cria e inicializa um novo nó.
     * @return O novo nó criado
     */
    protected NoArvoreB<K> alocaNo() {
        NoArvoreB<K> no = new NoArvoreB<>();
        no.setChaves((K[])new Comparable[2*grauMin-1]);
        no.setFilhos(new NoArvoreB[2*grauMin]);
        no.setFolha(true);
        return no;
    }
    
    /**
     * Classe auxiliar para retornar o resultado de busca de uma chave.
     */
    public class ResultadoBusca {
        private final NoArvoreB<K> no;
        private final int indiceChave;

        public ResultadoBusca(NoArvoreB<K> no, int indiceChave) {
            this.no = no;
            this.indiceChave = indiceChave;
        }

        public NoArvoreB<K> getNo() {
            return no;
        }

        public int getIndiceChave() {
            return indiceChave;
        }

        @Override
        public String toString() {
            return String.format("(%s, %d)", no, indiceChave);
        }

        @Override
        public boolean equals(Object obj) {
            boolean equal = false;
            if (obj instanceof ArvoreB.ResultadoBusca) {
                ResultadoBusca rb = (ResultadoBusca) obj;
                equal = no.equals(rb.no) && indiceChave == rb.indiceChave; 
            }
            return equal;
        }
        
    }
    
    /**
     * Busca o nó com a chave especificada.
     * @param x A subárvore a ser pesquisa
     * @param k A chave procurada
     * @return O resultado da busca ou {@code null}
     */
    protected ResultadoBusca busca(NoArvoreB<K> x, K k) {
        throw new RuntimeException("Não implementado");
    }
    
    /**
     * Busca o nó com a chave especificada.
     * @param k A chave procurada
     * @return O resultado da busca ou {@code null}
     */
    public ResultadoBusca busca(K k) {
        return busca(raiz, k);
    }
    
    /**
     * Divide um filho cheio de modo a abrir espaço para inserção.
     * O nó passado no primeiro argumento não pode estar cheio.
     * @param x Um nó não cheio
     * @param i O índice do filho cheio
     * @param y O filho cheio
     */
    protected void divideFilho(NoArvoreB<K> x, int i, NoArvoreB<K> y) {
        throw new RuntimeException("Não implementado");
    }
    
    /**
     * Insere uma nova chave em um nó não cheio.
     * O nó passado no primeiro argumento não pode estar cheio.
     * @param x Um nó não cheio
     * @param k A chave a ser inserida no nó
     */
    protected void insereNaoCheio(NoArvoreB<K> x, K k) {
        throw new RuntimeException("Não implementado");
    }
    
    /**
     * Insere a chave especificada n árvore.
     * @param k A chave a ser inserida
     */
    public void insere(K k) {
        throw new RuntimeException("Não implementado");
    }
    
    /**
     * Grava o nó especificado no armazenamento secundário.
     * Nesta implementação, toda a árvore permanece na memória, por isso
     * este método não necessita nenhuma ação.
     * Para implementações que usem outro tipo de armazenamento secundário,
     * deve ser sobrecarregado de acordo.
     * @param no O nó a ser gravado
     */
    protected void escritaNo(NoArvoreB<K> no) {
    }

    /**
     * Carrega o nó especificado do armazenamento secundário.
     * Nesta implementação, toda a árvore permanece na memória, por isso
     * este método não necessita nenhuma ação.
     * Para implementações que usem outro tipo de armazenamento secundário,
     * deve ser sobrecarregado de acordo.
     * @param no O nó a ser carregado 
     */
    protected void leituraNo(NoArvoreB<K> no) {
    }
    
    /**
     * Compara duas árvores B.
     * Os nós são percorridos recursivamente e comparados.
     * @param outro A árvore a ser compaprada
     * @return 
     */
    @Override
    public boolean equals(Object outro) {
        boolean equal;
        equal = outro instanceof ArvoreB;
        if (equal) {
            ArvoreB<K> a = (ArvoreB<K>) outro;
            NoArvoreB<K> r1 = getRaiz();
            NoArvoreB<K> r2 = a.getRaiz();
            equal = r1.equals(r2); 
            for (int i = 0; equal && i < r1.getNumChaves(); i++) {
                if (!r1.isFolha()) {
                    equal = r1.getFilho(i).equals(r2.getFilho(i));
                }
            }
        }
        return equal;
    }

    /**
     * Retorna um hash para a árvore.
     * @return O hash para a árvore.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.grauMin;
        hash = 29 * hash + Objects.hashCode(this.raiz);
        return hash;
    }

    /**
     * Retorna uma representação textual da árvore.
     * Percorre toda a árvore recursivamente.
     * O formato da representação é
     * [k0 k1 ...], ([m0 m1 ...]), [n0 n1 ...])
     * @param raiz A raiz da subárvore
     * @return Uma representação textual da árvore
     */
    protected String toString(NoArvoreB<K> raiz) {
        StringBuilder sb = new StringBuilder();
        if (raiz.isFolha()) {
            sb.append(raiz);
        } else {
            String s = Arrays.stream(raiz.getFilhos())
                    .limit(raiz.getNumChaves()+1)
                    .map(f -> toString(f))
                    .collect(Collectors.joining(",", "(" + raiz + ", (", "))"));
            sb.append(s);
        }
        return sb.toString();
    }
    
    /**
     * Retorna uma representação textual da árvore.
     * O formato da representação é
     * ([k0 k1 ...], ([m0 m1 ...]), [n0 n1 ...]))
     * @return Uma representação textual da árvore.
     */
    @Override
    public String toString() {
        return toString(raiz);
    }
}
