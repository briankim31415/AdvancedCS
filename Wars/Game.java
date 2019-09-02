/*public class Game {

    RingBuffer player1 = new RingBuffer(52);
    RingBuffer player2 = new RingBuffer(52);
    RingBuffer table = new RingBuffer(52);
    int playCount;

    public Game(RingBuffer p1, RingBuffer p2) {
        player1 = p1;
        player2 = p2;
        playCount = 0;
    }

    public void Play(){
        double p1card = player1.dequeue();
        double p2card = player2.dequeue();
        table.enqueue(p1card);
        table.enqueue(p2card);
        
        if(p1card > p2card) {
        	while(!table.isEmpty()) {
        		player1.enqueue(table.dequeue());
        	}
        	playCount++;
        } else if(p1card < p2card) {
        	while(!table.isEmpty()) {
        		player2.enqueue(table.dequeue());
        	}
        	playCount++;
        } else {
        	table.enqueue(player1.dequeue());
        	table.enqueue(player2.dequeue());
        	if(!player1.isEmpty() && !player2.isEmpty()) {
        		Play();
        	}
        }
    }

    public String Win() {
    	while (playCount < 10000) {
    		if(player1.isEmpty()) {
    			return "Player 2 wins!";
    		} 
    		if(player2.isEmpty()) {
    			return "Player 1 wins!";
    		}
    		Play();
    	}
    	return "Tie game stopped at 100000 plays.";
    }
}
*/

import java.util.*;
public class Game {
	RingBuffer playerOne = new RingBuffer(52);
	RingBuffer playerTwo = new RingBuffer(52);
	RingBuffer extra = new RingBuffer(52);
	int numplays;

	public Game(RingBuffer a, RingBuffer b){
		playerOne = a;
		playerTwo = b;
		numplays = 0;
	}

	public String Win(){
		while (numplays < 100000){
			if (playerTwo.isEmpty()){
				return "Player 1 wins!";
			}
			if (playerOne.isEmpty()){
				return "Player 2 wins!";
			}
			compare();
		}

		return "Tie game stopped at 100000 plays.";

	}

	public void compare() {
		double tempOne = playerOne.dequeue();
		double secondTempOne = playerTwo.dequeue();
		extra.enqueue(tempOne);
		extra.enqueue(secondTempOne);

		if (tempOne > secondTempOne) {
			while (!extra.isEmpty()){
				playerOne.enqueue(extra.dequeue());
			}
			numplays++;
		} else if (tempOne < secondTempOne) {
			while (!extra.isEmpty()){
				playerTwo.enqueue(extra.dequeue());
			}
			numplays++;
		} else {
			extra.enqueue(playerOne.dequeue());
			extra.enqueue(playerTwo.dequeue());
			if (!playerOne.isEmpty() && !playerTwo.isEmpty()) {
				compare();
			}
		}
	}
}