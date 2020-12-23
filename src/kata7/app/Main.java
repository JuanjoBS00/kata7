package kata7.app;

import kata7.control.BlockPresenter;
import kata7.control.Command;
import kata7.control.DownCommand;
import kata7.control.LeftCommand;
import kata7.control.RightCommand;
import kata7.control.UpCommand;
import java.util.Map;
import kata7.model.Block;
import javax.swing.JFrame;
import kata7.view.BlockDisplay;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import kata7.control.BlockPresenter;

public class Main  extends JFrame{
    public static void main(String[] args) {
        new Main().execute();
    }
    private Block block;
    private BlockDisplay blockDisplay;
    private BlockPresenter blockPresenter;
    private Map<String, Command> commands;
    
    public Main() {
        this.setTitle("Block shifter");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700,762);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
    }
    private void execute() {
        this.block = new Block(4,4);
        this.commands= createCommands();
        this.blockPresenter = new BlockPresenter(block,blockDisplay);
        this.setVisible(true);
    }
    private BlockPanel blockPanel() {
         BlockDisplay blockPanel = new BlockPanel(Block.MAX);
         this.blockDisplay = blockPanel;
         return (BlockPanel) blockPanel;
    }
    
    private HashMap<String,Command> createCommands() {
        HashMap<String,Command> comm = new HashMap<>();
        comm.put("L",new LeftCommand(block));
        comm.put("R",new RightCommand(block));
        comm.put("U",new UpCommand(block));
        comm.put("D",new DownCommand(block));
        return comm;
    }
    
    private Component toolbar(){
        JMenuBar bar = new JMenuBar();
        bar.setLayout(new FlowLayout(FlowLayout.CENTER));
        bar.add(button("L"));
        bar.add(button("U"));
        bar.add(button("D"));
        bar.add(button("R"));
        return bar;
    }
    
    private JButton button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                commands.get(name).execute();
            }
        });
        return button;
    }
    
}