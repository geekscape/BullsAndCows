package com.dansdev.bulls.and.cows;

import java.util.Random;

public class SecretNumbers {
	
	private int fSecretNumberArray[];
	private Random r = new Random();
	public int fBulls;
	public int fCows;

	public SecretNumbers()
	{
		fSecretNumberArray = new int[9];
		for (int i = 0; i < 9; i++)
		{
			fSecretNumberArray[i] = i + 1;
		}
		startGame();
		
		fBulls = 0;
		fCows = 0;
	}
	
	public void startGame()
    {
    	for (int i = 0; i < 9; i ++)
    	{
    		int n = 9;
    		while ( n > 1)
    		{
    		// new random index in the range 0 to n
    		int k = (r.nextInt(9));
    		n--; // n last pertinent index
    		swap( n, k ); 
    		}
    	}
    }
	
	public void swap(int n, int k)
	{
		int temp;
		temp = fSecretNumberArray[n];
		fSecretNumberArray[n] = fSecretNumberArray[k];
		fSecretNumberArray[k] = temp;	
	}
	
	public boolean guess( String aGuessString )
	{
		// start fresh
		fBulls = 0;
		fCows = 0;
		char localGuess[] = aGuessString.toCharArray();

		for ( int i = 0; i < 4; i++ )
		{
			int lTest = localGuess[i] - '0';

			if ( fSecretNumberArray[i] == lTest )
			{
				fBulls++;
			}
			else
			{
				for ( int j = 0; j < 4; j++ )
				{
					if ( fSecretNumberArray[j] == lTest )
					{
						fCows++;
						break;
					}
				}
			}
		}
		return fBulls == 4;
		
	}
	
	public char getBulls()
	{
		char bulls = (char)fBulls;
		return bulls;
	}
	
	public char getCows()
	{
		char cows = (char)fCows;
		return cows;
	}
	
//	public String getBulls()
//	{
//		char nBulls[] = new char[1];
//		nBulls[1] = (char)fBulls;
//		String result = nBulls.toString();
//		return result;
//	}
//	
//	public String getCows()
//	{
//		char nCows[] = new char[1];
//		nCows[1] = (char)fCows;
//		String result = nCows.toString();
//		return result;
//	}
}
