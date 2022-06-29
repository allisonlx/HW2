import org.w3c.dom.Node;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Polynomial {

    LinkedList<Term> terms;

    public Polynomial(){
        terms = new LinkedList<Term>();
    }

    public Polynomial(Polynomial original){
        if (original == null ){
            throw new NullPointerException();
        }
        this.terms = new LinkedList<Term>();
        this.terms.addAll(original.terms);

    }

    public Term getTerm(int index){
        return this.terms.get(index);
    }

    public void setTerm(int index, Term t){
        this.terms.set(index, t);
    }

    public int getNumTerms(){
        return this.terms.size();
    }


    public void addTerm(Term t){
        boolean duplicate = false;
        if (this.terms.size() == 0){
            this.terms.add(t);
        }
        else {
            for (int i = 0; i < this.terms.size(); i++) {
                if (this.terms.get(i).getExponent() == t.getExponent()) {
                    Term original = terms.get(i);
                    terms.set(i, new Term(original.getCoefficient() + t.getCoefficient(), t.getExponent()));
                    duplicate = true;
                }
            }
            if (!duplicate) {
                this.terms.add(t);
            }
            this.terms.sort(Comparator.comparing(Term::getExponent));
            Collections.reverse(this.terms);
        }
    }

    @Override
    public String toString(){
        String output = "";
        if (this.terms.size() == 0){
            return "0";
        }
        this.terms.sort(Comparator.comparing(Term::getExponent));
        Collections.reverse(this.terms);
        String term1;
        if(terms.get(0).getCoefficient() < 0){
            output += this.terms.get(0).toString();
        }
        else {
            String t1 = terms.get(0).toString().substring(1);
            output += t1;
        }
        for (int i = 1; i < this.terms.size(); i++){
            output += this.terms.get(i).toString();
        }
        return output;
    }

    @Override
    public boolean equals(Object other){
        if (other == null || !(other instanceof Polynomial)){
            return false;
        }
        Polynomial that = (Polynomial)other;
        return this.terms.equals(that.terms);
    }

    public void clear(){
        this.terms = new LinkedList<Term>();
    }


    public void add(Polynomial p){
        for (Term t : p.terms){
            this.addTerm(t);
        }
    }

}
