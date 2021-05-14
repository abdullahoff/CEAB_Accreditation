package src;

/**
* @brief ...
* @param ...
* @param ...
*/

public interface Measures {
    public double[] measures() throws UnsupportedOperationException;
    public double[] measures(IndicatorT ind) throws UnsupportedOperationException;
    public double[] measures(AttributeT att) throws UnsupportedOperationException;        
}
