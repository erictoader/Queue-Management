import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private final View v;

    public Controller(View v) {
        this.v = v;

        v.addStartSimListener(new StartSimListener());
        v.addClearListener(new ClearListener());
    }

    class StartSimListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Integer.parseInt(v.getNumberOfClients().getText());
                Integer.parseInt(v.getNumberOfQueues().getText());
                Integer.parseInt(v.getTMaxSimulation().getText());
                Integer.parseInt(v.getTMinArrival().getText());
                Integer.parseInt(v.getTMaxArrival().getText());
                Integer.parseInt(v.getTMinService().getText());
                Integer.parseInt(v.getTMaxService().getText());
                v.stylize();
                v.startSimulation();
            }
            catch (Exception ex){
                displayCorectness();
                v.showError("Fields surrounded by red are invalid.");
            }
        }
    }

    class ClearListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.reset();
        }
    }

    public void displayCorectness() {
        try{
            Integer.parseInt(v.getNumberOfClients().getText());
            v.getNumberOfClients().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                    new Color(25,196,52), new Color(20,20,20)));
        }
        catch (Exception ex){
            v.getNumberOfClients().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                    new Color(103,20,19), new Color(20,20,20)));
        }

        try{
            Integer.parseInt(v.getNumberOfQueues().getText());
            v.getNumberOfQueues().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                    new Color(25,196,52), new Color(20,20,20)));
        }
        catch (Exception ex){
            v.getNumberOfQueues().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                    new Color(103,20,19), new Color(20,20,20)));
        }

        try{
            Integer.parseInt(v.getTMaxService().getText());
            v.getTMaxService().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                    new Color(25,196,52), new Color(20,20,20)));
        }
        catch (Exception ex){
            v.getTMaxService().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                    new Color(103,20,19), new Color(20,20,20)));
        }

        try{
            Integer.parseInt(v.getTMinService().getText());
            v.getTMinService().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                    new Color(25,196,52), new Color(20,20,20)));
        }
        catch (Exception ex){
            v.getTMinService().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                    new Color(103,20,19), new Color(20,20,20)));
        }

        try{
            Integer.parseInt(v.getTMaxSimulation().getText());
            v.getTMaxSimulation().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                    new Color(25,196,52), new Color(20,20,20)));
        }
        catch (Exception ex){
            v.getTMaxSimulation().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                    new Color(103,20,19), new Color(20,20,20)));
        }

        try{
            Integer.parseInt(v.getTMaxArrival().getText());
            v.getTMaxArrival().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                    new Color(25,196,52), new Color(20,20,20)));
        }
        catch (Exception ex){
            v.getTMaxArrival().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                    new Color(103,20,19), new Color(20,20,20)));
        }

        try{
            Integer.parseInt(v.getTMinArrival().getText());
            v.getTMinArrival().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                    new Color(25,196,52), new Color(20,20,20)));
        }
        catch (Exception ex){
            v.getTMinArrival().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                    new Color(103,20,19), new Color(20,20,20)));
        }

    }
}
