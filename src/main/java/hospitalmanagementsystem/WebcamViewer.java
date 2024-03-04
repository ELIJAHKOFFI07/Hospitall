package hospitalmanagementsystem;

import javax.swing.*;

;

public class WebcamViewer extends JFrame {
    /*
    private DaemonThread myThread = null;
    private VideoCapture webSource = null;
    private final Mat frame = new Mat(1000, 1000, 1);
    private final MatOfByte mem = new MatOfByte();

    private class DaemonThread implements Runnable {
        protected volatile boolean runnable = false;
        private JLabel display;

        public DaemonThread(JLabel displayLabel) {
            this.display = displayLabel;
        }

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (webSource.grab()) {
                        try {
                            webSource.retrieve(frame);
                            Highgui.imencode(".bmp", frame, mem);
                            Image im = new ImageIcon(mem.toArray()).getImage();

                            BufferedImage buff = (BufferedImage) im;
                            Graphics g = display.getGraphics();

                            if (g.drawImage(buff, 1, 1, display.getWidth(), display.getHeight(), null)) {
                                if (runnable == false) {
                                    this.wait();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public WebcamViewer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);

        myThread = new DaemonThread(new JLabel());
        Thread thread = new Thread(myThread);
        thread.setDaemon(true);
        thread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WebcamViewer().setVisible(true);
        });
    }
    @FXML
    public void showWebcam() {
        JFrame frame = new JFrame("Webcam Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        org.bytedeco.opencv.opencv_videoio.VideoCapture capture = new org.bytedeco.opencv.opencv_videoio.VideoCapture(0); // 0 pour la webcam par défaut

        org.bytedeco.opencv.opencv_core.Mat mat = new org.bytedeco.opencv.opencv_core.Mat();

        final BufferedImage[] image = {new BufferedImage(mat.cols(), mat.rows(), BufferedImage.TYPE_3BYTE_BGR)};
        MatOfByte matOfByte = new MatOfByte();

        JLabel label = new JLabel(new ImageIcon(image[0]));
        frame.add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        // Rafraîchir l'image de la webcam dans la fenêtre
        Timer timer = new Timer(33, e -> {
            capture.read(mat);
            Imgcodecs.imencode(".png", mat, matOfByte);
            byte[] data = matOfByte.toArray();

            // Vérifiez si la taille de l'image a changé
            if (image[0].getWidth() != mat.cols() || image[0].getHeight() != mat.rows()) {
                image[0] = new BufferedImage(mat.cols(), mat.rows(), BufferedImage.TYPE_3BYTE_BGR);
                label.setIcon(new ImageIcon(image[0]));
                frame.pack();
            }

            image[0].getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);
            label.repaint();
        });
        timer.start();
    }
    */

}
