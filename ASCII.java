import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

public final class ASCII {
	boolean negative;

	public ASCII() {
		this(false);
	}

	public ASCII(final boolean negative) {
		this.negative = negative;
	}

	public String convert(final BufferedImage image) throws InterruptedException {
		StringBuilder sb = new StringBuilder((image.getWidth() + 1) * image.getHeight());
		for (int y = 0; y < image.getHeight(); y++) {
			if (sb.length() != 0) sb.append("\n");
			for (int x = 0; x < image.getWidth(); x++) {
				Color pixelColor = new Color(image.getRGB(x, y));
				double gValue = (double) pixelColor.getRed() * 0.2989 + (double) pixelColor.getBlue() * 0.5870 + (double) pixelColor.getGreen() * 0.1140;
				final char s = negative ? returnStrNeg(gValue) : returnStrPos(gValue);
				sb.append(s);
			}
		}
                String[] lines = sb.toString().split("\\n");
                for(String s: lines){
                    System.out.println(s);
                   Thread.sleep(100);
                }
		return sb.toString();
	}
	private char returnStrPos(double g)//takes the grayscale value as parameter
	{
		final char str;

		if (g >= 230.0) {
			str = ' ';
		} else if (g >= 200.0) {
			str = '.';
		} else if (g >= 180.0) {
			str = '*';
		} else if (g >= 160.0) {
			str = ':';
		} else if (g >= 130.0) {
			str = 'o';
		} else if (g >= 100.0) {
			str = '&';
		} else if (g >= 70.0) {
			str = '8';
		} else if (g >= 50.0) {
			str = '#';
		} else {
			str = '@';
		}
		return str; 

	}
        
	private char returnStrNeg(double g) {
		final char str;

		if (g >= 230.0) {
			str = '@';
		} else if (g >= 200.0) {
			str = '#';
		} else if (g >= 180.0) {
			str = '8';
		} else if (g >= 160.0) {
			str = '&';
		} else if (g >= 130.0) {
			str = 'o';
		} else if (g >= 100.0) {
			str = ':';
		} else if (g >= 70.0) {
			str = '*';
		} else if (g >= 50.0) {
			str = '.';
		} else {
			str = ' ';
		}
		return str;

	}

	public static void main(String[] args) throws InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
					//Change image path//
                            File f = new File("aami.png");
						final BufferedImage image;
                            try {
                                image = ImageIO.read(f);
                                if (image == null) throw new IllegalArgumentException(f + " is not a valid image.");
						final String ascii = new ASCII().convert(image);
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            } catch (InterruptedException ex) {
                               System.out.println(ex.getMessage());
                            }
				
				System.exit(0);
			}
		});
	}

}
