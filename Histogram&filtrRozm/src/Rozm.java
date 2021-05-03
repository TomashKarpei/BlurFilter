	import java.awt.*;
	import java.awt.image.BufferedImage;
	import java.io.*;
	import javax.imageio.ImageIO;
	import javax.swing.JFrame;
	import java.math.*;
	public class Rozm {
	 BufferedImage image;
	 int width;
	 int height;

	 public Rozm() {

		 try {
			 //odczyt obrazu z pliku

			 File input = new File("lenna.jpg");
			 
			 image = ImageIO.read(input);
			 width = image.getWidth();
			 height = image.getHeight();
			 //tworzenie maski macierzy
			 float[][] M = new float[][] { {1.0f/9.0f,1.0f/9.0f,1.0f/9.0f}, {1.0f/9.0f,1.0f/9.0f,1.0f/9.0f}, {1.0f/9.0f,1.0f/9.0f,1.0f/9.0f}};


			 //odczyt pixeli obrazu w dwóch pêtlach po kolumnach i wierszach

			 for(int i=1; i<height-1; i++){
			 for(int j=1; j<width-1; j++){
				 Color c1 = new Color(image.getRGB(j, i));
				 double pomoc_r = 0;
				 double pomoc_g = 0;
				 double pomoc_b = 0;
				 int red1 = (int)(c1.getRed());
				 int green1 = (int)(c1.getGreen());
				 int blue1 = (int)(c1.getBlue());;
			     int red, green, blue;


			   for (int k=-1;k<=1;k++) {
			     for (int l=-1; l<=1;l++) {

			       //odczyt sk³adowych koloru RGB

			        Color c = new Color(image.getRGB(j+l, i+k));

			        red = (int)(c.getRed());
			        green = (int)(c.getGreen());
			        blue = (int)(c.getBlue());

			       pomoc_r += red * M[k+1][l+1];
			       pomoc_g += green * M[k+1][l+1];
			       pomoc_b += blue * M[k+1][l+1];

			   }
			   }
			   //puntky rozmycia
		       red = (int) pomoc_r;
		       green = (int) pomoc_g;
		       blue = (int) pomoc_b;



			 //System.out.println("\n" + pomoc_r +" "+ pomoc_g + " " + pomoc_b );

			 if (red>=0 && red <=255) {}
			 else red = red1;

			 if (green>=0 && green <=255) {}
			 else green = green1;

			 if (blue >=0 && blue <=255) {}
			 else blue = blue1;

			  
			 Color newColor = new Color(red, green, blue);
			 image.setRGB(j,i,newColor.getRGB());



			 } //koniec dwóch pêtli po kolumnach i wierszach obrazu
			 }

			 //zapis do pliku zmodyfikowanego obrazu

			 File ouptut = new File("fltr.jpg");
			 ImageIO.write(image, "jpg", ouptut);

			 } catch (Exception e) {}
			 }

	 static public void main(String args[]) throws Exception
	 {
		 Rozm obj = new  Rozm();
	 }
	}