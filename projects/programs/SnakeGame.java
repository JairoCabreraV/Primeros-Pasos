import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JFrame {
    public SnakeGame() {
        this.add(new GamePanel());
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}

class GamePanel extends JPanel implements ActionListener {
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private final int UNIT_SIZE = 25;
    private final int DELAY = 100;
    private final ArrayList<Point> snake = new ArrayList<>();
    private int bodyParts = 3;
    private int applesEaten;
    private Point apple;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;
    private Random random;

    public GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        snake.clear();
        for (int i = 0; i < bodyParts; i++) {
            snake.add(new Point(UNIT_SIZE * (bodyParts - i), 0));
        }
        spawnApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void spawnApple() {
        int x = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        int y = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        apple = new Point(x, y);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            // Dibujar la comida
            g.setColor(Color.red);
            g.fillOval(apple.x, apple.y, UNIT_SIZE, UNIT_SIZE);

            // Dibujar el gusanito
            for (int i = 0; i < snake.size(); i++) {
                if (i == 0) g.setColor(Color.green);
                else g.setColor(new Color(45, 180, 0));
                Point p = snake.get(i);
                g.fillRect(p.x, p.y, UNIT_SIZE, UNIT_SIZE);
            }

            // Puntuación
            g.setColor(Color.white);
            g.setFont(new Font("Ink Free", Font.BOLD, 20));
            g.drawString("Puntuación: " + applesEaten, 10, 20);
        } else {
            gameOver(g);
        }
    }

    public void move() {
        // Mover el cuerpo
        for (int i = snake.size() - 1; i > 0; i--) {
            snake.set(i, new Point(snake.get(i - 1)));
        }
        // Mover la cabeza
        Point head = snake.get(0);
        switch (direction) {
            case 'U': head.y -= UNIT_SIZE; break;
            case 'D': head.y += UNIT_SIZE; break;
            case 'L': head.x -= UNIT_SIZE; break;
            case 'R': head.x += UNIT_SIZE; break;
        }
        snake.set(0, head);
    }

    public void checkApple() {
        if (snake.get(0).equals(apple)) {
            bodyParts++;
            snake.add(new Point(snake.get(snake.size() - 1)));
            applesEaten++;
            spawnApple();
        }
    }

    public void checkCollisions() {
        Point head = snake.get(0);
        // Colisión con el cuerpo
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) running = false;
        }
        // Colisión con bordes
        if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT) running = false;

        if (!running) timer.stop();
    }

    public void gameOver(Graphics g) {
        // Puntuación final
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (WIDTH - metrics.stringWidth("Game Over")) / 2, HEIGHT / 2);

        g.setFont(new Font("Ink Free", Font.BOLD, 20));
        g.drawString("Puntuación final: " + applesEaten, (WIDTH - metrics.stringWidth("Puntuación final: " + applesEaten)) / 2, HEIGHT / 2 + 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT: if (direction != 'R') direction = 'L'; break;
                case KeyEvent.VK_RIGHT: if (direction != 'L') direction = 'R'; break;
                case KeyEvent.VK_UP: if (direction != 'D') direction = 'U'; break;
                case KeyEvent.VK_DOWN: if (direction != 'U') direction = 'D'; break;
            }
        }
    }
}
