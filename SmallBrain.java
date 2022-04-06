/**
 * A simple implementation based on SimpleBrain.
 * 
 */
public class SmallBrain extends SimpleBrain 
{
    public double rateBoard(Board board)
    {
        double score = super.rateBoard(board);
        return (10000 - score);
    }
    
    public String toString()
    {
        return "Small Brain";
    }
}
