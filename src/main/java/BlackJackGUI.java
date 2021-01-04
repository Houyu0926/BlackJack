import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlackJackGUI implements ActionListener{
	
	private final BlackJack bj = new BlackJack();
	private final JPanel playerPanel = new JPanel();
	private final JPanel bankPanel = new JPanel();
	private final JButton anotherButton = new JButton("Another Card");
	private final JButton noMoreButton = new JButton("No More Card");
	private final JButton resetButton = new JButton("Reset");
	
	
	public BlackJackGUI(){
		
		JFrame frame = new JFrame("BlackJack GUI");
		JPanel topPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		
		anotherButton.setActionCommand("Another Card");
		anotherButton.addActionListener(this);
			
		noMoreButton.setActionCommand("No More Card");
		noMoreButton.addActionListener(this);
		
		resetButton.setActionCommand("Reset");
		resetButton.addActionListener(this);
		
		frame.setMinimumSize(new Dimension(640,480));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		topPanel.setLayout (new FlowLayout(FlowLayout.LEFT));
		topPanel.add(anotherButton);
		topPanel.add(noMoreButton);
		topPanel.add(resetButton);
		frame.add(topPanel,BorderLayout.NORTH);
		frame.pack();
		
		centerPanel.setLayout (new GridLayout(2,1));
		
		bankPanel.setBorder(BorderFactory.createTitledBorder("Bank"));
		centerPanel.add(bankPanel,BorderLayout.NORTH);
		
		playerPanel.setBorder(BorderFactory.createTitledBorder("Player"));
		centerPanel.add(playerPanel,BorderLayout.SOUTH);
		
		
		
		frame.add(centerPanel,BorderLayout.CENTER);
		frame.setVisible(true);
		}
			
		public void actionPerformed(ActionEvent e) {
			
			anotherButton.setEnabled(true);
			noMoreButton.setEnabled(true);
			resetButton.setEnabled(true);
			
		switch(e.getActionCommand()) { 
			case "Another Card": 
			
			try{ 
			bj.playerDrawAnotherCard();
			
			if(bj.getPlayerBest() > 21){anotherButton.setEnabled(false);}
				
				try{
				updatePlayerPanel();
				updateBankPanel();
					}catch(FileNotFoundException ex) {
				System.out.println(ex.getMessage());
				System.exit(-1);
				}
			
			}catch(EmptyDeckException ex){
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
				}
			break;
			
			case "No More Card": 
			
			try{
				bj.bankLastTurn();
				try{
				updatePlayerPanel();
				updateBankPanel();		
					}catch(FileNotFoundException ex) {
				System.out.println(ex.getMessage());
				System.exit(-1);
				}
				
				anotherButton.setEnabled(false);
				noMoreButton.setEnabled(false);
				resetButton.setEnabled(true);
				
				}catch(EmptyDeckException ex){
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
				}
			break;
			
			case "Reset":
			
			try{
				
				bj.reset();
				try{
				updatePlayerPanel();
				updateBankPanel();
				}catch(FileNotFoundException ex) {
				System.out.println(ex.getMessage());
				System.exit(-1);
				}
				
				resetButton.setEnabled(false);
				
				}catch(EmptyDeckException ex){
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
				}
			break;
			}
			}
		
		
		public void addToPanel(JPanel p, String token)throws FileNotFoundException{
		File file = new File("./img/card_"+token+".png");
		
		if (!file.exists()) {
			throw new FileNotFoundException("Can't find "+file.getPath());
			}
			
			ImageIcon icon = new ImageIcon(file.getPath()); 
			JLabel label = new JLabel(icon);
			
			p.setLayout (new FlowLayout(FlowLayout.LEFT));
			p.add(label); 
			
		}
		
		public void updatePlayerPanel()throws FileNotFoundException{
			playerPanel.removeAll();
			
			int size = bj.getPlayerCardList().size();
			String val="",col="";
			
			for(int i=0;i < size;i++){
			val = bj.getPlayerCardList().get(i).getValueSymbole();
			col = bj.getPlayerCardList().get(i).getColorName();
			addToPanel(playerPanel, col+"_"+val);
		}
		
			JLabel bestp = new JLabel("Player best : "+bj.getPlayerBest(),SwingConstants.RIGHT);
			playerPanel.setLayout (new FlowLayout(FlowLayout.LEFT));
			playerPanel.add(bestp);
			
			if(bj.getPlayerBest()==21){
				addToPanel(playerPanel, "blackjack");
				}
				
			if(bj.isBankWinner()==false && bj.isPlayerWinner()==true && bj.isGameFinished()==true){
				addToPanel(playerPanel, "winner");
				}else if (bj.isBankWinner()==true && bj.isPlayerWinner()==false && bj.isGameFinished()==true){
					addToPanel(playerPanel, "looser");
					}else if(bj.isBankWinner()==false && bj.isPlayerWinner()==false && bj.isGameFinished()==true){
						addToPanel(playerPanel, "draw");
						}
			
			playerPanel.updateUI();
			}
		
		public void updateBankPanel()throws FileNotFoundException{
			bankPanel.removeAll();
			
			int size = bj.getBankCardlist().size();
			String val="",col="";
			
			for(int i=0;i < size;i++){
			val = bj.getBankCardlist().get(i).getValueSymbole();
			col = bj.getBankCardlist().get(i).getColorName();
			addToPanel(bankPanel, col+"_"+val);
			}
		
			JLabel bestb = new JLabel("Bank best : "+bj.getBankBest(),SwingConstants.RIGHT);
			bankPanel.setLayout (new FlowLayout(FlowLayout.LEFT));
			bankPanel.add(bestb);
			
			if(bj.getBankBest()==21){
				addToPanel(bankPanel, "blackjack");
				}
				
			if(bj.isBankWinner()==true && bj.isPlayerWinner()==false && bj.isGameFinished()==true){
				addToPanel(bankPanel, "winner");
				}else if (bj.isBankWinner()==false && bj.isPlayerWinner()==true && bj.isGameFinished()==true){
					addToPanel(bankPanel, "looser");
					}else if(bj.isBankWinner()==false && bj.isPlayerWinner()==false && bj.isGameFinished()==true){
						addToPanel(bankPanel, "draw");
						}
			
			bankPanel.updateUI();
			}
	
	public static void main(String[] args) {
		new BlackJackGUI();
		}
}
