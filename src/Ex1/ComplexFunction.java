package Ex1;

/**
 * This class represents a Complex_Function of Polynoms
 * and can deal with arithmetic methods such as mult,divid, plus etc..
 * Complex Function has 3 main variables such as:
 * op - stand for Operation Like:plus,mult,divid etc..
 * right - stand for the right function of the Complex function
 * left - stand for the left function of the Complex Function (Can be a Complex Function as well)
 */
public class ComplexFunction implements complex_function {
    private Operation op;
    private function right;
    private function left;

    @Override
/**
 * This Method can get 2 Complex Functions ans give them the Plus Operation
 * @param f1 - the function that will get the Plus Operation with our Complex Function
 */
    public void plus(function f1) {
        function f = new ComplexFunction(getOp().toString(), left(), right());
        this.left = f.copy();

        this.op = Operation.Plus;
        this.right = f1.copy();
    }

    @Override
    /**
     * This Method can get 2 Complex Functions ans give them the Mult Operation
     * @param f1 - the function that will get the Mult Operation with our Complex Function
     */
    public void mul(function f1) {
        function f = new ComplexFunction(getOp().toString(), left(), right());
        this.left = f.copy();

        this.op = Operation.Times;
        this.right = f1.copy();
    }

    @Override
    /**
     * This Method can get 2 Complex Functions ans give them the Divid Operation
     * @param f1 - the function that will get the Divid Operation with our Complex Function
     */
    public void div(function f1) {
        function f = new ComplexFunction(getOp().toString(), left(), right());
        this.left = f.copy();

        this.op = Operation.Divid;
        this.right = f1.copy();
    }

    @Override
    /**
     * This Method can get 2 Complex Functions ans give them the Max Operation
     * @param f1 - the function that will get the Max Operation with our Complex Function
     */
    public void max(function f1) {
        function f = new ComplexFunction(getOp().toString(), left(), right());
        this.left = f.copy();

        this.op = Operation.Max;
        this.right = f1.copy();
    }

    @Override
    /**
     * This Method can get 2 Complex Functions ans give them the Min Operation
     * @param f1 - the function that will get the Min Operation with our Complex Function
     */
    public void min(function f1) {
        function f = new ComplexFunction(getOp().toString(), left(), right());
        this.left = f.copy();

        this.op = Operation.Min;
        this.right = f1.copy();
    }

    @Override
    /**
     * This Method can get 2 Complex Functions ans give them the Comp Operation
     * @Param f1 - the function that will get the Comp Operation with our Complex Function
     */
    public void comp(function f1) {
        function f = new ComplexFunction(getOp().toString(), left(), right());
        this.left = f.copy();

        this.op = Operation.Comp;
        this.right = f1.copy();
    }

    @Override
    /**
     * This Method return the left side of the Complex Function
     * @return left side of the Complex Function
     */
    public function left() {
        return this.left;

    }

    @Override
    /**
     * This Method return the Right side of the Complex Function
     * @return right side of the Complex Function
     */
    public function right() {
        return this.right;

    }

    @Override
    /**
     * This Method return the Operation of the Complex Function
     * @return Operation of Complex Function
     */
    public Operation getOp() {
        return this.op;

    }

    @Override
    /**
     * This Method return the Value of the Complex Function in point x that will be given
     * @param x - The Double number that we will placing in the Complex Function.
     * @return the Value of the Complex Function in point x
     */
    public double f(double x) {
        switch (getOp()) {
            case Plus:
                return left.f(x) + right.f(x);
            case Times:
                return left.f(x) * right.f(x);
            case Divid:
                Double temp=right.copy().f(x);
                if(temp==0){
                    throw new RuntimeException("Cant Divid By zero!");
                }
                return left.f(x) / right.f(x);
            case Max:
                return Math.max(left.f(x), right.f(x));
            case Min:
                return Math.min(left.f(x), right.f(x));
            case Comp:
                return left.f(right.f(x));
            case None:
                return left.f(x);
            case Error:
                throw new RuntimeException("Invalid Operation");
        }
        return 0.0;
    }

    /**
     * This Method can get 2 Complex function and do an Equals test between them.
     * ****WARNING**** :
     * This Method is not fully Working Because We not testing Equals deeply,
     * we only check the outcome of a given x dot between -20 to 20 with jumping of 0.5 every iteration
     * and return True if the outcome will be identical with all given dots.
     * ****WARNING****
     * @param o Should be a Complex Function and if not, Throws an Exception
     * @return True if the outcome will be identical with all given dots. if not return False.
     */
    public boolean equals(Object o) {
        boolean b = true;
        if (o instanceof function) {
            function fun = (function) o;
            for (double i = -20; i < 20; i = i + 0.5) {
                if (Math.abs(this.f(i) - fun.f(i)) > 0.00001) {
                    b = false;
                }
            }
        } else {
            b = false;
        }
        return b;
    }

    @Override
    /**
     * This Method Copy a Complex Function and return a copy of it
     * @return a Copy of a Complex Function
     */
    public function copy() {
        function fun = new ComplexFunction(this);
        return fun;
    }

    /**
     * This Method is a Constructor Of a Complex Function. it gets a function and convert it to a Complex Function
     * and if the Method Identify the function as a simple Polynom it will build a Complex Function with:
     * op=None left=Polynom right=null
     * @param f1 - a given function that will be converted into Complex Function
     */
    public ComplexFunction(function f1) {
        if (f1 instanceof ComplexFunction) {
            ComplexFunction co = (ComplexFunction) f1;
            right = co.right().copy();
            left = co.left().copy();
            op = co.getOp();
        } else {
            right = null;
            left = f1.copy();
            op = Operation.None;
        }
    }

    /**
     * This Method is a Constructor Of a Complex Function. it gets 3 Variables that represents an Operation
     * and function left and function right.
     *
     * @param s - a given string that represents an Operation such as Mult, Divid, Plus etc..
     * @param left - a given Function that represents the left side of the Complex Function that we will build
     * @param right - a given Function that represents the Right side of the Complex Function that we will build
     */
    public ComplexFunction(String s, function left, function right) {
        this.right = right.copy();
        this.left = left.copy();
        this.op = Operation_String(s);
    }

    public ComplexFunction() {
        ComplexFunction co = null;
    }
    /**
     * This Method is a Converter from String to an Operation
     * @param s - a String that represents an Operation
     * @return The String s as an Operation.
     */
    private Operation Operation_String(String s) {
        String st=s.toLowerCase();
        switch (st) {
            case "plus":
                return Operation.Plus;
            case "mul":
                return Operation.Times;
            case "times":
                return Operation.Times;
            case "div":
                return Operation.Divid;
            case "divid":
                return Operation.Divid;
            case "max":
                return Operation.Max;
            case "min":
                return Operation.Min;
            case "comp":
                return Operation.Comp;
            case "none":
                return Operation.None;
            case "error":
                return Operation.Error;
            default:
                throw new RuntimeException("Invalid Operation");
        }
    }

    /**
     * This Method is a Converter from Operation to a String
     * @param o - an Operation From a given Operation Bank
     * @return The Operation o as a String.
     */
    private String String_Operation(Operation o) {
        switch (o) {
            case Times:
                return "Times";
            case Error:
                return "Error";
            case Comp:
                return "Comp";
            case Min:
                return "Min";
            case Max:
                return "Max";
            case Divid:
                return "Div";
            case Plus:
                return "Plus";
            default:
                throw new RuntimeException("Invalid Operation");
        }
    }

    /**
     * a Simple toString Method that returns the Complex Function as a String
     * @return - the Complex Function as a String.
     */
    @Override
    public String toString() {
        if (getOp() == Operation.None) {
            return left().toString();
        }
        return String_Operation(getOp()) + "(" + left() + ", " + right() + ")";
    }

    /**
     * This Method take a Given String and Building From it a ComplexFunction/Polynom
     * This Method is Working recursively and peeling the given String from inside to outside
     * and keeping the correct order of the String
     * @param s - a String that represents a Complex Function
     * @return a Complex Function Contains Operation and the Left and Right Sides of the Complex Function
     */
    @Override
    public function initFromString(String s) {
        ComplexFunction c=null;
        try {
            if (s.lastIndexOf(')')==s.length()-1) {
                String st=s.substring(0,s.indexOf('('));
                Operation o=Operation_String(st);
                int BracketCounter = 0;
                int j = 0;
                boolean flag = true;
                for (int k = s.indexOf('(')+1; k < s.length() - 1 && flag; k++) {
                    if(s.charAt(k)=='('){
                        BracketCounter--;
                    }
                    if(s.charAt(k)==')'){
                        BracketCounter++;
                    }
                    if (BracketCounter == 0 && s.charAt(k) == ',') {
                        flag = false;
                        j = k;
                    }
                }
                int temp=s.indexOf('(')+1;
                function l=initFromString(s.substring(temp,j));
                int temp2=s.length()-1;
                function r=initFromString(s.substring(j+1,temp2));
                c=new ComplexFunction(o.toString(),l,r);
            }
            else{
                String str=s.substring(s.indexOf('(')+1,s.length());
                return new Polynom(str);
            }
        }
        catch (Exception e){
            throw new RuntimeException("Invalid String, function pattern not found/Operation not found/Invalid Polynom writen");
        }
        return c;
    }
}
