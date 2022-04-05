import java.util.ArrayList;

/**
 * This class uses the factory design pattern to provide a list of
 *      objects that implement the Brain interface
 */
public class BrainFactory
{
    /**
     * Create a list of references to objects whose classes implement the Brain
     *      interface
     *
     * @return a list of referenes to objects whose classes implement the Brain
     *      interface
     */
    public static ArrayList<Brain> createBrains()
    {
        
        SimpleBrain simple_brain = new SimpleBrain();
        SmallBrain small_brain = new SmallBrain();
        ArrayList<Brain> list = new ArrayList<Brain>();
        
        list.add(simple_brain);
        list.add(small_brain);
        
        return list;
    }
}
