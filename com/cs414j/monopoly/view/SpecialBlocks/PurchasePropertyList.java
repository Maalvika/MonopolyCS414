package com.cs414j.monopoly.view.SpecialBlocks;

public final class PurchasePropertyList {

	// LV stands for left vertical blocks
	public enum LVBlocks {
		Mediterranean_Avenue(10,790), Baltic_Avenue(10,630), Reading_Railroad(10,470), 
		Oriental_Avenue(10,390),Vermont_Avenue(10,230), Connecticut_Avenue(10,150);
		
		private int xpoint, ypoint;

		private LVBlocks(int x, int y) {
			xpoint = x;
			ypoint = y;
		}

		public int getXpoint() {
			return xpoint;
		}

		public int getYpoint() {
			return ypoint;
		}

	}

	// UH stand for upper horizontal blocks
	public enum UHBlocks {
		St_Charles_Place(170,70),Electric_Co(250,70),State_Avenue(330,70),
		Virginia_Avenue(410,70),Pennsylvania_Railroad(490,70),St_James_Place(570,70),
		Tenesse_Avenue(730,70),New_York_Avenue(810,70);
		
		private int xpoint, ypoint;

		private UHBlocks(int x, int y) {
			xpoint = x;
			ypoint = y;
		}

		public int getXpoint() {
			return xpoint;
		}

		public int getYpoint() {
			return ypoint;
		}
	}
	
	// RV stands for Right Vertical blocks
	public enum RVBlocks {
		Kentucky_Avenue(890,150), Indiana_Avenue(890,310), Illinois_Avenue(890,390),
		B_O_Railroad(890,470),Atlantic_Avenue(890,550), Ventnor_Avenue(890,630),
		Water_Works(890,710), Marvin_Gardens(890,790);
		
		private int xpoint, ypoint;

		private RVBlocks(int x, int y) {
			xpoint = x;
			ypoint = y;
		}

		public int getXpoint() {
			return xpoint;
		}

		public int getYpoint() {
			return ypoint;
		}
	}
	 
	//LH stand for lower horizontal blocks
	public enum LHBlocks {
		
		Pacific_Avenue(810,870), North_Carolina_Avenue(730,870), Pennsylvania_Avenue(570,870),
		Short_Line(490,870),Park_Place(330,870), Boardwalk(170,870);
		
		private int xpoint, ypoint;

		private LHBlocks(int x, int y) {
			xpoint = x;
			ypoint = y;
		}

		public int getXpoint() {
			return xpoint;
		}

		public int getYpoint() {
			return ypoint;
		}
	}

}
