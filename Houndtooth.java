public class Houndtooth {
	
	private boolean top;
	private int position;
	private int x;
	private int y;
	
	private static int xLength;
	private static int yLength;
	
	private static int xInterval;
	private static int yInterval;
	
	private static String s1 = "#";
	private static String s2 = " ";
	
	public static void setLengths(int xLen, int yLen) {
		
		xLength = xLen;
		yLength = yLen;
		
	}
	
	public static void setIntervals(int xInt, int yInt) {
		
		xInterval = xInt;
		yInterval = yInt;
		
	}
	
	public Houndtooth() {
		
		this.top = false;
		this.position = 0;
		this.x = 0;
		this.y = 0;
		
	}
	
	public Houndtooth(boolean top, int position, int x, int y) {
		
		this.top = top;
		this.position = position;
		this.x = x;
		this.y = y;
		
	}
	
	public Houndtooth nextRight() {
		
		int nextPos = (this.position+1)%xLength;
		boolean nextTop = (nextPos == 0 ? !this.top : this.top);
		
		return new Houndtooth(nextTop, nextPos, this.x+1, this.y);
		
	}
	
	public Houndtooth nextLeft() {
		
		int nextPos = (this.position-1+xLength)%xLength;
		boolean nextTop = (this.position == 0 ? !this.top : this.top);
		
		return new Houndtooth(nextTop, nextPos, this.x-1, this.y);
		
	}
	
	public Houndtooth nextDown() {
		
		int nextPos = (this.position-1+yLength)%yLength;
		boolean nextTop = (this.position == 0 ? !this.top : this.top);
		
		return new Houndtooth(nextTop, nextPos, this.x, this.y+1);
		
	}
	
	public Houndtooth nextUp() {
		
		int nextPos = (this.position+1)%yLength;
		boolean nextTop = (nextPos == 0 ? !this.top : this.top);
		
		return new Houndtooth(nextTop, nextPos, this.x, this.y-1);
		
	}
	
	public String toString() {
		
		int i;
		int iInterval;
		
		if(this.top) {
			
			i = x;
			iInterval = xInterval;
			
		} else {
			
			i = y;
			iInterval = yInterval;
			
		}
		
		return i % (iInterval*2) < iInterval ? s1 : s2;
		
	}
	
	private static Houndtooth[][] makeWeave(int xsize, int ysize) {
		
		Houndtooth[][] arr = new Houndtooth[xsize][ysize];
		
		for(int x = 0; x < arr.length; x++) {
			
			arr[x][0] = (x!=0 ? arr[x-1][0].nextRight() : new Houndtooth());
			
			for(int y = 1; y < arr[x].length; y++) {
				
				arr[x][y] = arr[x][y-1].nextDown();
				
			}
		}
		
		return arr;
		
	}
	
	public static void main(String[] args) {
		
		String[] defaults = {
			"xSize=50",
			"ySize=50",
			"xLength=2",
			"yLength=2",
			"xInterval=4",
			"yInterval=4"
		};
		
		ArgumentHandler ah = new ArgumentHandler(defaults, "=");
		ah.parse(args, "=");
		
		setLengths(ah.getInt("xLength"), ah.getInt("yLength"));
		setIntervals(ah.getInt("xInterval"), ah.getInt("yInterval"));
		
		Houndtooth[][] arr = makeWeave(ah.getInt("xSize"), ah.getInt("ySize"));
		
		for(int y = 0; y < arr[0].length; y++) {
			for(int x = 0; x < arr.length; x++) {
				
				System.out.print(arr[x][y].toString());
				
			}
			
			System.out.println();
			
		}
		
	}
}
