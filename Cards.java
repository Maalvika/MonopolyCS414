
public class Cards {
	int cost;
	int rentInitial;
	int rentFirstHouse;
	int rentSecondHouse;
	int rentThirdHouse;
	int rentFourthHouse;
	int rentHotel;
	int mortgageValue;
	int houseCost;
	int hotelCost;
	int rent2RROwned;
	int rent3RROwned;
	int rent4RROwned;
	
	int verticalFirstGrid[][]= {{0,0,0,0,0,0,0,0,0,0},{60,2,10,30,90,160,250,30,50,50},{0,0,0,0,0,0,0,0,0,0},
			              {60,2,10,30,90,160,250,30,50,50},{200,0,0,0,0,0,0,0,0,0},
			              {200,25,0,50,100,200,100,0,0,0,0},{100,6,30,90,270,400,550,50,50,50},
			              {0,0,0,0,0,0,0,0,0,0},{100,6,30,90,270,400,550,50,50,50},
			              {120,8,40,100,300,450,600,60,50,50},{0,0,0,0,0,0,0,0,0,0}};
	
	int horizontalAboveGrid[][]= {{0,0,0,0,0,0,0,0,0,0},{140,10,50,150,450,625,750,70,100,100},{150,20,100,75,0,0,0,0,0,0},
			{140,10,50,150,450,625,750,70,100,100},{160,12,60,180,500,700,900,80,100,100},
            {200,25,0,50,100,200,100,0,0,0,0},{180,14,70,200,550,750,950,90,100,100},
            {0,0,0,0,0,0,0,0,0,0},{180,14,70,200,550,750,950,90,100,100},
            {200,16,80,220,600,800,1000,100,100,100},{0,0,0,0,0,0,0,0,0,0}};
	
	int verticalSecondGrid[][]= {{0,0,0,0,0,0,0,0,0,0},{220,18,90,250,700,875,1050,110,150,150},{0,0,0,0,0,0,0,0,0,0},
			{220,18,90,250,700,875,1050,110,150,150},{240,20,100,300,750,925,1100,120,150,150},
            {200,25,0,50,100,200,100,0,0,0,0},{260,22,110,330,800,975,1150,130,150,150},
            {260,22,110,330,800,975,1150,130,150,150},{150,20,100,0,0,0,0,0,0,0},
            {280,24,120,360,850,1025,1200,140,150,150},{0,0,0,0,0,0,0,0,0,0}};
	
	int horizontalBelowGrid[][]= {{0,0,0,0,0,0,0,0,0,0},{300,26,130,390,900,1100,1275,150,200,200},{300,26,130,390,900,1100,1275,150,200,200},
			{0,0,0,0,0,0,0,0,0,0},{320,28,150,450,1000,1200,1400,160,200,200},
            {200,25,0,50,100,200,100,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
            {350,35,175,500,1100,1300,1500,175,200,200},{100,0,0,0,0,0,0,0,0,0},
            {400,50,200,600,1400,1700,2000,200,200,200},{0,0,0,0,0,0,0,0,0,0}};
	
	
	
	
	public int[] setCards(int i,int j){
		if (j==0){
			//vertical first grid
			if ((i==1)|(i==2)|(i==4)|(i==7)|(i==9))
			{
				int temp = 10-i;
				int value[]={cost, rentInitial, rentFirstHouse, rentSecondHouse, rentThirdHouse, rentFourthHouse,
					     rentHotel, mortgageValue, houseCost,hotelCost};
					for (int k=0; k<10; k++)
						{
							value[k] = verticalFirstGrid[temp][k];
						}
					return value;
			}
			if(i==5)
			{
				int temp = 10-1;
				int value[] = {cost,rentInitial,0,rent2RROwned,rent3RROwned, rent4RROwned, mortgageValue};
				for (int k =0 ;k<7;k++)
				{
					value[k]= verticalFirstGrid[10-i][k];	
				}
				return value;
			}
		}
		if (j==10){
			//vertical second grid
		}
		if (i==0){
			//horizontal above grid
		}
		if (i ==10){
			//horizontal below grid
		}
		//System.out.println(information[1][1]);
	}
	public void getCardsValue(int i, int j){
		int[] information = setCards(i,j);
			
		//System.out.println(information[1][1]);
	}

	public static void main(String []args){
		int information[][]= {{0,0,0,1},{1,1,1,1}};
		System.out.println(information);
	}
}

