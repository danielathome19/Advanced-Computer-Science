package Q3.CS161LabJ;

/**
 * A class that can perform simple mathematical operations with double precision.
 * @author joel
 * @version sp13
 */
public class Calculator
{
    //constants to represent operations
    public static final int EQUALS = 0;
    public static final int ADD = 1;
    public static final int SUBTRACT = 2;
    public static final int MULTIPLY = 3;
    public static final int DIVIDE = 4;

    private double value; //the value stored in our registry
    private int lastOperation;

    /**
     * Creates a new Calculator with the given initial value stored.
     * @param init The initial value of the calculator
     */
    public Calculator(double init, int operation)
    {
        value = init;
        lastOperation = operation;
    }

    /**
     * Creates a new Calculator with an initial value of 0.
     */
    public Calculator()
    {
        this(0.0, EQUALS);
    }

    /**
     * Returns the current stored value.
     * @return the value
     */
    public double getValue()
    {
        return value;
    }

    /**
     * Adds the given number to the stored value.
     * @param num The number to add to the stored value
     */
    public void add(double num)
    {
        value += num;
    }

    /**
     * Subtracts the given number to the stored value.
     * @param num The number to subtract from the stored value
     */
    public void subtract(double num)
    {
        value -= num;
    }

    /**
     * Multiplies the stored value by the given number.
     * @param num The number to multiply the stored value by
     */
    public void multiply(double num)
    {
        value *= num;
    }

    /**
     * Divides the stored value by the given number.
     * @param num The number to divide the stored value by
     */
    public void divide(double num)
    {
        if(num != 0)
            value /= num;
    }

    /**
     * Clears the stored value (resets it to 0).
     */
    public void clear()
    {
        value = 0.0;
        lastOperation = 0;
    }

    /**
     * Apply the last operation on the value computed so far and the value passed as parameter. Then it
     * saves the current operation as the last operation. 
     * @param val The second operand
     * @param operation the operation to be applied to the previous operand
     * @return newly computed value
     */
    public double applyOperation(double val, int operation)
    {
        if(lastOperation == EQUALS) //if we haven't hit anything
            value = val; //add the display value to the engine, to make it work
        else if(lastOperation == ADD)
            add(val);
        else if(lastOperation == SUBTRACT)
            subtract(val);
        else if(lastOperation == MULTIPLY)
            multiply(val);
        else if(lastOperation == DIVIDE)
            divide(val);
        lastOperation = operation;
        return value;
    }

}
