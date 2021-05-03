	import java.awt.*;
	import java.awt.image.BufferedImage;
	import java.io.*;
	import javax.imageio.ImageIO;
	import javax.swing.JFrame;
	import java.math.*;
	public class filtr_gauss {
	 BufferedImage image;
	 int width;
	 int height;

	 public filtr_gauss() {

		 try {
			 //odczyt obrazu z pliku

			 File input = new File("lenna.jpg");

			 image = ImageIO.read(input);
			 width = image.getWidth();
			 height = image.getHeight();
			 int x, y, z, b = 2;
			//tworzenie maski macierzy
			 int[][] M = new int[][] { {1,b,1}, {b,b*b,b}, {1,b,1}};
			 double pomoc_r, pomoc_g, pomoc_b;

			 //odczyt pixeli obrazu w dwóch pêtlach po kolumnach i wierszach

			 for(int i=1; i<height-1; i++){
			 for(int j=1; j<width-1; j++){
			   
			   pomoc_r = 0;
			   pomoc_g = 0;
			   pomoc_b = 0;


			   for (int k=-1;k<=1;k++) {
			     for (int l=-1; l<=1;l++) {

			       //odczyt sk³adowych koloru RGB

			        Color c = new Color(image.getRGB(j+k, i+l));

			        int red = (int)(c.getRed());
			        int green = (int)(c.getGreen());
			        int blue = (int)(c.getBlue());

			       pomoc_r += red  * M[k+1][l+1];
			       pomoc_g += green * M[k+1][l+1];
			       pomoc_b += blue * M[k+1][l+1];

			   }
			   }
			 //puntky rozmycia
			   pomoc_r = 200*Math.exp(-(i*i +j*j)/(2*pomoc_r*pomoc_r));
			   pomoc_g = 200*Math.exp(-(i*i +j*j)/(2*pomoc_g*pomoc_g));
			   pomoc_b = 200*Math.exp(-(i*i +j*j)/(2*pomoc_b*pomoc_b));
			   
			   
			   x = (int) pomoc_r;
			   y = (int) pomoc_g;
			   z = (int) pomoc_b;
			   
			 System.out.println("\n" + pomoc_r +" "+ pomoc_g + " " + pomoc_b );


			 if (x>=0 && x <=255) {}
			 else x = 0;

			 if (y>=0 && y<=255) {}
			 else y = 0;

			 if (z>=0 && z <=255) {}
			 else z = 0;

			   

			 Color newColor = new Color(x, y, z);
			 image.setRGB(j,i,newColor.getRGB());



			 } //koniec dwóch pêtli po kolumnach i wierszach obrazu
			 }

			 //zapis do pliku zmodyfikowanego obrazu

			 File ouptut = new File("filtr_gauss.jpg");
			 ImageIO.write(image, "jpg", ouptut);

			 } catch (Exception e) {}
			 }

	 static public void main(String args[]) throws Exception
	 {
		 filtr_gauss  obj = new  filtr_gauss();
	 }
}

