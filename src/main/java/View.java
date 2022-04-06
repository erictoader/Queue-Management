import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class View extends JFrame {
    private boolean isStarted;

    // SETUP STAGE
    private final JTextField numberOfClients = new JTextField(5);
    private final JTextField numberOfQueues = new JTextField(5);
    private final JTextField tMaxSimulation = new JTextField(5);
    private final JTextField tMinArrival = new JTextField(5);
    private final JTextField tMaxArrival = new JTextField(5);
    private final JTextField tMinService = new JTextField(5);
    private final JTextField tMaxService = new JTextField(5);

    private final JButton startSimButton = new JButton("Start Simulation");
    private final JButton clearButton = new JButton("Clear");

    // RUNNING STAGE

    private JTextArea display = new JTextArea();

    public View(){
        this.isStarted = false;

        numberOfClients.setEditable(true);
        numberOfQueues.setEditable(true);
        tMaxService.setEditable(true);
        tMinService.setEditable(true);
        tMaxSimulation.setEditable(true);
        tMaxArrival.setEditable(true);
        tMinArrival.setEditable(true);

        setupInterface();
    }

    private void setupInterface(){
        this.setVisible(false);
        this.frameInit();

        stylize();

        JPanel window = new JPanel();
        window.setBackground(new Color(44,45,47));
        window.setOpaque(true);
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Space

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 7;
        c.ipady = 2;

        JLabel s1 = new JLabel(" ");
        setSpaceColor(s1);
        window.add(s1, c);

        // col space

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;

        JLabel sC1 = new JLabel("     ");
        setSpaceColor(sC1);
        window.add(sC1, c);

        // Number of Clients:

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.ipadx = 20;

        JLabel noc = new JLabel("Number of clients: ");
        setLabelColor(noc);
        window.add(noc, c);

        // col space

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;

        JLabel sC2 = new JLabel("  ");
        setSpaceColor(sC2);
        window.add(sC2, c);

        // NOC field

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 1;
        c.gridwidth = 2;

        window.add(numberOfClients, c);

        // col space

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 6;
        c.gridy = 1;
        c.gridwidth = 1;

        JLabel sC3 = new JLabel("  ");
        setSpaceColor(sC3);
        window.add(sC3, c);

        // Space

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 7;

        JLabel s2 = new JLabel(" ");
        setSpaceColor(s2);
        window.add(s2, c);

        //  Number of queues:

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        c.ipadx = 20;

        JLabel noq = new JLabel("Number of queues: ");
        setLabelColor(noq);
        window.add(noq, c);

        // NOQ field

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 3;
        c.gridwidth = 2;

        window.add(numberOfQueues, c);

        // Space

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 7;

        JLabel s3 = new JLabel(" ");
        setSpaceColor(s3);
        window.add(s3, c);

        // Simulation time:

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        c.ipadx = 20;

        JLabel st = new JLabel("Simulation time: ");
        setLabelColor(st);
        window.add(st, c);

        // ST field

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 5;
        c.gridwidth = 2;

        window.add(tMaxSimulation, c);

        // Space

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 7;

        JLabel s4 = new JLabel(" ");
        setSpaceColor(s4);
        window.add(s4, c);

        // Time min arrival:

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 2;
        c.ipadx = 20;

        JLabel tmina = new JLabel("Time min arrival: ");
        setLabelColor(tmina);
        window.add(tmina, c);

        // TMinA field

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 7;
        c.gridwidth = 2;

        window.add(tMinArrival, c);

        // Space

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 7;

        JLabel s5 = new JLabel(" ");
        setSpaceColor(s5);
        window.add(s5, c);

        // Time max arrival:

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 9;
        c.gridwidth = 2;
        c.ipadx = 20;

        JLabel tmaxa = new JLabel("Time max arrival: ");
        setLabelColor(tmaxa);
        window.add(tmaxa, c);

        // TMaxA field

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 9;
        c.gridwidth = 2;

        window.add(tMaxArrival, c);

        // Space

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 7;

        JLabel s6 = new JLabel(" ");
        setSpaceColor(s6);
        window.add(s6, c);

        // Time min service:

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 11;
        c.gridwidth = 2;
        c.ipadx = 20;

        JLabel tmins = new JLabel("Time min service: ");
        setLabelColor(tmins);
        window.add(tmins, c);

        // TMinS field

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 11;
        c.gridwidth = 2;

        window.add(tMinService, c);

        // Space

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 12;
        c.gridwidth = 7;

        JLabel s7 = new JLabel(" ");
        setSpaceColor(s7);
        window.add(s7, c);

        // Time max service:

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 13;
        c.gridwidth = 2;
        c.ipadx = 20;

        JLabel tmaxs = new JLabel("Time max service: ");
        setLabelColor(tmaxs);
        window.add(tmaxs, c);

        // TMaxS field

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 13;
        c.gridwidth = 2;

        window.add(tMaxService, c);

        // Space

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 14;
        c.gridwidth = 7;
        c.gridheight = 2;
        c.ipady = 10;

        JLabel s8 = new JLabel(" ");
        setSpaceColor(s8);
        window.add(s8, c);

        // Start button

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 16;
        c.gridwidth = 1;
        c.ipadx = 0;

        window.add(startSimButton, c);

        // Clear button

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 16;
        c.gridwidth = 2;
        c.ipadx = 70;

        window.add(clearButton, c);

        // Space

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 17;
        c.gridwidth = 7;
        c.gridheight = 3;
        c.ipady = 20;

        JLabel s9 = new JLabel(" ");
        setSpaceColor(s9);
        window.add(s9, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 20;
        c.gridwidth = 7;

        JLabel ver = new JLabel("Queue Management Application v1.0 by Eric Toader");
        setLabelColor(ver);
        ver.setFont(new Font("Helvetica", Font.PLAIN, 8));
        window.add(ver, c);

        ///
        this.add(window);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("");
        this.setResizable(false);
        this.setVisible(true);

        rootPane.putClientProperty("apple.awt.fullWindowContent", true);
        rootPane.putClientProperty("apple.awt.transparentTitleBar", true);
    }

    private void runningInterface() {
        this.setVisible(false);
        this.frameInit();

        this.setBounds(0, 0, 650, 700);
        this.setLayout(null);

        JPanel window = new JPanel();
        window.setBackground(new Color(43, 45, 47));
        window.setLayout(null);
        window.setBounds(0, 0, 650, 700);

        display.setBackground(new Color(58, 61, 64));
        display.setForeground(new Color(231,231,231));
        display.setFont(new Font("Helvetica", Font.PLAIN, 16));

        display.setBounds(15, 50, 620, 625);
        display.setEditable(false);
        display.setLineWrap(true);
        display.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(display);
        scroll.setBackground(new Color(58, 61, 64));
        scroll.setForeground(new Color(231,231,231));
        scroll.setBounds(15, 50, 620, 625);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        window.add(scroll);

        this.add(window);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("");
        this.setResizable(false);
        this.setVisible(true);

        rootPane.putClientProperty("apple.awt.fullWindowContent", true);
        rootPane.putClientProperty("apple.awt.transparentTitleBar", true);
    }

    private void setSpaceColor(JLabel sl) {
        sl.setForeground(new Color(44,45,47));
        sl.setBackground(new Color(44,45,47));
        sl.setOpaque(true);
    }

    private void setLabelColor(JLabel l) {
        l.setForeground(new Color(231,231,231));
        l.setBackground(new Color(44,45,47));
        l.setFont(new Font("Helvetica", Font.PLAIN, 14));
        l.setOpaque(true);
    }

    private void setTextFieldColor(JTextField tf) {
        tf.setBackground(new Color(59,61,64));
        tf.setForeground(new Color(231,231,231));
        tf.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                new Color(20,20,20), new Color(20,20,20)));
        tf.setFont(new Font("Helvetica", Font.PLAIN, 12));
    }

    public void stylize(){
        setTextFieldColor(numberOfClients);
        setTextFieldColor(numberOfQueues);
        setTextFieldColor(tMaxService);
        setTextFieldColor(tMinService);
        setTextFieldColor(tMaxSimulation);
        setTextFieldColor(tMaxArrival);
        setTextFieldColor(tMinArrival);
    }

    public void reset() {
        numberOfClients.setText("");
        numberOfQueues.setText("");
        tMaxService.setText("");
        tMinService.setText("");
        tMaxSimulation.setText("");
        tMaxArrival.setText("");
        tMinArrival.setText("");
    }

    public boolean isStarted() {
        return this.isStarted;
    }

    public void startSimulation() {
        this.isStarted = true;
        runningInterface();
    }

    public JTextField getNumberOfClients() {
        return numberOfClients;
    }

    public JTextField getNumberOfQueues() {
        return numberOfQueues;
    }

    public JTextField getTMaxSimulation() {
        return tMaxSimulation;
    }

    public JTextField getTMinArrival() {
        return tMinArrival;
    }

    public JTextField getTMaxArrival() {
        return tMaxArrival;
    }

    public JTextField getTMinService() {
        return tMinService;
    }

    public JTextField getTMaxService() {
        return tMaxService;
    }

    public void setDisplay(String msg) {
        this.display.setText(msg);
    }

    public void updateDisplay(String msg) {
        this.display.append(msg);
    }

    public void addClearListener(ActionListener clr){
        clearButton.addActionListener(clr);
    }

    public void addStartSimListener(ActionListener start) {
        startSimButton.addActionListener(start);
    }

    public void showError(String message){
        JOptionPane.showMessageDialog(this,message);
    }
}

