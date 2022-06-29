public class Term implements Comparable, Cloneable {

    /**** Instance Variables ****/
    public int coefficient;
    public int exponent;

    /**** Constants ****/
    public static final int DEFAULT_COEFFICIENT = 1;
    public static final int DEFAULT_EXPONENT = 1;

    public int getCoefficient(){
        return this.coefficient;
    }

    public void setCoefficient(int coefficient){
        this.coefficient = coefficient;
    }

    public int getExponent(){
        return this.exponent;
    }

    public void setExponent(int exponent){
        this.exponent = exponent;
    }

    public void setAll(int coefficient, int exponent){
        this.setCoefficient(coefficient);
        this.setExponent(exponent);
    }




    public String toString(){
        String output = "";
        if (coefficient == 1 && exponent != 0){
            output += "+";
        }
        else if (coefficient > 0){
            output += "+" + coefficient;
        }
        else if (coefficient == -1 && exponent != 0){
            output += "-";
        }
        else if (coefficient < 0){
            output += String.valueOf(coefficient);
        }
        else {
            return "";
        }


        if (exponent == 0){

        }
        else if (exponent == 1){
            output += "x";
        }
        else {
            output += "x^" + exponent;
        }
        return output;
    }

    public boolean equals(Object other){
        if (other == null || !(other instanceof Term)){
            return false;
        }
        Term that = (Term)other;
        return (this.coefficient == that.coefficient) && (this.exponent == that.exponent);

    }



    //Full Constructor
    public Term(int coefficient, int exponent){
        this.setAll(coefficient, exponent);
    }

    //Default Constructor
    public Term(){
        this(DEFAULT_COEFFICIENT, DEFAULT_EXPONENT);
    }

    //Copy Constructor
    public Term(Term other){
        if (other == null){
            throw new NullPointerException();
        }
        else {
            this.setAll(other.getCoefficient(), other.getExponent());
        }
    }

    //Overloaded Constructor
    public Term(String term){
        int[] values = parseString(term);
        this.setAll(values[0], values[1]);
    }




    public int[] parseString(String term){

        boolean positiveCo = true;
        boolean positiveExp = true;
        int[] values = new int[2];
        if (term == null || term.equals("0")){
            values[0] = 0;
            values[1] = 1;
            return values;
        }
        if (term.charAt(0) == '-'){
            positiveCo = false;
        }

        if (term.contains("x^")){

            if (term.indexOf('x') == 1){
                values[0] = 1;
            }
            else {
                values[0] = Integer.parseInt(term.substring(1, term.indexOf('x')));
            }
            String s = term.substring(term.indexOf('^') + 1);
            if (s.charAt(0) == '-'){
                positiveExp = false;
                values[1] = Integer.parseInt(term.substring(term.indexOf('^')+ 2));
            }
            else {
                values[1] = Integer.parseInt(term.substring(term.indexOf('^') + 1));
            }

        }
        else if (term.contains("x")){
            values[1] = 1;
            if (term.indexOf('x') == 1) {
                values[0] = 1;
            }

            else {
                values[0] = Integer.parseInt(term.substring(1, term.indexOf('x')));
            }
        }
        else {
            values[0] = Integer.parseInt(term);
            values[1] = 0;
            return values;
        }

        if (!positiveCo){
            values[0] *= -1;
        }
        if (!positiveExp){
            values[1] *= -1;
        }
        return values;

    }

    @Override
    public int compareTo(Object o) {
        if (o == null || !(o instanceof Term)){
            throw new NullPointerException();
        }
        Term that = (Term)o;
        return this.exponent - that.exponent;
    }



    @Override
    public Object clone() throws CloneNotSupportedException{
       return super.clone();
    }
}
