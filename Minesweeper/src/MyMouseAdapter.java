import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {

	//----------------------------------------------------------------------------------------------------------------------------------
	//Mouse Press
	public void mousePressed(MouseEvent e) {
		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return;
			}
		}
		JFrame myFrame = (JFrame) c;
		MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
		Insets myInsets = myFrame.getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		e.translatePoint(-x1, -y1);
		int x = e.getX();
		int y = e.getY();
		myPanel.x = x;
		myPanel.y = y;
		myPanel.mouseDownGridX = myPanel.getGridX(x, y);
		myPanel.mouseDownGridY = myPanel.getGridY(x, y);
		myPanel.repaint();
		switch (e.getButton()) {
		case 1:		//Left mouse button
			break; 
		case 3:		//right mouse button
			break;
		default:    
			//Do nothing
			break;
		}
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	//Mouse Release
	public void mouseReleased(MouseEvent e) {
		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return;
			}
		}
		JFrame myFrame = (JFrame)c;
		MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
		Insets myInsets = myFrame.getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		e.translatePoint(-x1, -y1);
		int x = e.getX();
		int y = e.getY();
		myPanel.x = x;
		myPanel.y = y;
		int gridX = myPanel.getGridX(x, y);
		int gridY = myPanel.getGridY(x, y);
		switch (e.getButton()) {
		case 1:		//Left mouse button
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Do nothing
					} else {
						
						myPanel.selectGrid(myPanel.mouseDownGridX, myPanel.mouseDownGridY);
						myPanel.repaint();
					}			
				}
			}
			break;
		case 3:		
			//---------------------------------------------------------------------------------------------------------------------------------
			//Right mouse button
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Do nothing
					} else {

						if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] != Color.WHITE){

							if((myPanel.colorArray[myPanel.mouseDownGridX][ myPanel.mouseDownGridY]==Color.YELLOW)){
								//Remove Flag
								myPanel.colorArray[myPanel.mouseDownGridX][ myPanel.mouseDownGridY]=Color.LIGHT_GRAY;
								myPanel.repaint();
								myPanel.flagCounter++;
							}else{
								//Set Flag
								myPanel.colorArray[myPanel.mouseDownGridX][ myPanel.mouseDownGridY]=Color.YELLOW;
								myPanel.repaint();
								myPanel.flagCounter--;
							}
						}
					}
				}
			}
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	
}