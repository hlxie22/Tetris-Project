import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.event.*;

public class JBrainTetris extends JTetris
{

    private Brain brain;
    private boolean enabled = false; 
    private JComboBox box;
    private JButton button;
    private Move goal;

    /**
     * Constructor for objects of class JBrainTetris
     */
    public JBrainTetris(int width, int height)
    {
        super(width, height);

    }

    @Override
    public Container createControlPanel()
    {

        ArrayList<Brain> list1 = BrainFactory.createBrains();

        Brain[] list = new Brain[list1.size()];

        for (int i = 0; i < list1.size() ; i++)
        {
            list[i] = list1.get(i);
        }
        
        this.brain = list[0];
        
        box = new JComboBox(list);
        ComboBoxListener listener1= new ComboBoxListener();
        box.addActionListener(listener1);

        
        button = new JButton("Enable Brain");
        ButtonListener listener2= new ButtonListener();
        button.addActionListener(listener2);

        Container container = super.createControlPanel();
        container.add(box);
        container.add(button);

        return container;
    }

    public class ComboBoxListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            brain = (Brain) box.getSelectedItem();

        }
    }

    public class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            change_Enabled();

            if (enabled)
            {
                button.setText("Disable Brain");
            }

            else
            {
                button.setText("Enable Brain");
            }

        }
    }

    public void change_Enabled()
    {
        this.enabled = !enabled;
    }

    @Override
    public Piece pickNextPiece()
    {
        Piece piece = super.pickNextPiece();
        int limit_height = TOP_SPACE + HEIGHT;

        if (this.enabled)
        {
            this.goal = this.brain.bestMove(this.board, piece, limit_height);
        }

        return piece;
    }

    @Override
    public void tick(int verb)
    {

        if (enabled)
        {

            // Determine whether rotation is necessary

            if (this.goal.getPiece() != this.currentPiece)
            {
                super.tick(ROTATE);
            }

            // Determine whether left-right movement is necessary

            if (this.goal.getX() > this.currentX)
            {
                super.tick(RIGHT);
            }

            if (this.goal.getX() < this.currentX)
            {
                super.tick(LEFT);
            }

            super.tick(DOWN);
        }

        else 
        {
            super.tick(verb);
        }

    }
}
