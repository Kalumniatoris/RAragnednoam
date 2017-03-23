package ka2.tesa;

import java.util.ArrayList;
import java.util.Random;

import ka2.tesa.elements.Player;

public class Board {
	int[] costs; // wall,generator, container
	Element[][] map;
	private ArrayList<Player> players;
	private int size;
	private int nPlayers;
	private int nGenCap = 100;

	public Board(int size) {
		map = new Element[size][size];
		this.setSize(size);
		this.setnPlayers(0);
		initMap();

		setPlayers(new ArrayList<Player>());
		costs = new int[] { 1, 10, 100 }; // costs of placing walls, generators
											// and containers

	}

	public void initMap() {
		for (int x = 0; x < size; x += 1) {
			for (int y = 0; y < size; y += 1) {
				this.map[x][y] = new Element(0, x, y, 0, -1);
			}
		}
		for (int x = 0; x < size; x += 1) {
			this.map[x][0].update(1, 0, -1);

			this.map[x][size - 1].update(1, 0, -1);

			this.map[0][x].update(1, 0, -1);

			this.map[size - 1][x].update(1, 0, -1);
		}
	}

	public void addPlayer(Player player) {
		getPlayers().add(player);
		Random rnd = new Random();
		int tmp;
		int x;
		int y;
		do {
			x = rnd.nextInt(size);
			y = rnd.nextInt(size);
			tmp = this.map[x][y].getType();

		} while (tmp != 0);
		this.map[x][y].update(4, 0, player.getId());
		player.setPosX(x);
		player.setPosY(y);
		this.setnPlayers(this.getnPlayers() + 1);
		player.setPower(100);
	}

	public void turn() {
		for (Element[] elements : map) {
			for (Element element : elements) {
				this.run(element);
			}
		}
		for (Player player : getPlayers()) {
			this.playerMove(player);
		}
	}

	private void run(Element element) {
		switch (element.getType()) {
		case 1: // Wall. Walls do nothing
			break;
		case 3: // Generator
			element.setPower(Math.min(
					element.getPower() + countAround(element, 3) + 1,
					element.getCapacity()));
			break;
		default: //
			break;
		}

	}

	private int countAround(Element element, int type) {
		int px = element.getPosX();
		int py = element.getPosY();
		int count = 0;

		if (map[px + 1][py].getType() == type)
			count += 1;
		if (map[px + 1][py + 1].getType() == type)
			count += 1;
		if (map[px + 1][py - 1].getType() == type)
			count += 1;
		if (map[px][py + 1].getType() == type)
			count += 1;
		if (map[px][py - 1].getType() == type)
			count += 1;
		if (map[px - 1][py - 1].getType() == type)
			count += 1;
		if (map[px - 1][py].getType() == type)
			count += 1;
		if (map[px - 1][py + 1].getType() == type)
			count += 1;

		return count;
	}

	private boolean inRange(int x, int min, int max) {
		if (x < min || x >= max)
			return false;
		return true;
	}

	private void posPlayer(Player p, int nx, int ny) {
		if (inRange(nx, 0, this.getSize()) && inRange(ny, 0, this.getSize())) {

			p.setPosY(ny);
			p.setPosX(nx);
		}
	}

	private int[][] look(Player p, int radius) {

		int px = p.getPosX();
		int py = p.getPosY();
		Element[][] look = getView(map, px - radius, px + radius, py - radius,
				py + radius);
		int view[][] = new int[2 * radius + 1][2 * radius + 1];

		for (int x = 0; x < 2 * radius + 1; x += 1) {
			for (int y = 0; y < 2 * radius + 1; y += 1) {
				view[x][y] = look[x][y].getType();
			}
		}

		return view;
	}

	private void playerMove(Player player) {
		int look[][];
		int px = player.getPosX();
		int py = player.getPosY();

		look = look(player, 5);

		String option = player.run(player.getId(), player.getPower(),
				player.getPosX(), player.getPosY(), look); // TODO arguments

		if (option == null)
			return;
		switch (option) {
		case "UP":
			if (this.map[px][py + 1].getType() != 1) {

				this.posPlayer(player, px, py + 1);
			}
			break;
		case "DOWN":
			if (this.map[px][py - 1].getType() != 1) {
				this.posPlayer(player, px, py - 1);
			}
			break;
		case "LEFT":
			if (this.map[px - 1][py].getType() != 1) {
				this.posPlayer(player, px - 1, py);
			}
			break;
		case "RIGHT":
			if (this.map[px + 1][py].getType() != 1) {
				this.posPlayer(player, px + 1, py);
			}
			break;
		case "GENERATOR":
			if (this.map[px][py].getType() == 0
					&& player.getPower() >= this.costs[1]) {
				player.setPower(player.getPower() - this.costs[1]);
				this.map[px][py].update(3, 0, -1);
				this.map[px][py].setCapacity(nGenCap);
			}
			break;
		case "WALL":
			if (this.map[px][py].getType() == 0
					&& player.getPower() >= this.costs[0]) {
				player.setPower(player.getPower() - this.costs[0]);
				this.map[px][py].update(1, 0, -1);
			}
			break;
		case "CONTAINER":
			if (this.map[px][py].getType() == 0
					&& player.getPower() >= this.costs[2]) {
				player.setPower(player.getPower() - this.costs[2]);
				this.map[px][py].update(4, 0, player.getId());
			}
			break;
		case "GET": // power from generator (anybody) or container (only owner)
			if (this.map[px][py].getType() == 3
					|| (this.map[px][py].getType() == 4 && this.map[px][py]
							.getId() == player.getId())) {
				if (player.getPower() + this.map[px][py].getPower() <= player
						.getCapacity()) {
					int p = this.map[px][py].getPower();
					player.setPower(player.getPower() + p);
					this.map[px][py].setPower(0);
				} else {
					int p = player.getCapacity() - player.getPower();
					player.setPower(player.getCapacity());
					this.map[px][py].setPower(this.map[px][py].getPower() - p);
				}
			}

			break;
		case "PUT": // power to container
			if (this.map[px][py].getType() == 4) {
				this.map[px][py].setPower(this.map[px][py].getPower()
						+ player.getPower());
				player.setPower(0);
			}

			break;
		case "WAIT":
			break;
		default:
			break;

		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getScore(Player player) {
		int score = 0;
		for (Element[] element : map) {
			for (Element el : element) {
				if (el.getType() == 4 && el.getId() == player.getId()) {
					score += el.getPower();
				}
			}
		}
		return score;
	}

	public int getnPlayers() {
		return nPlayers;
	}

	public void setnPlayers(int nPlayers) {
		this.nPlayers = nPlayers;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public Object[][] getPart(Object[][] source, int minx, int maxx, int miny,
			int maxy) { // min inclusive, max exclusive
		Object[][] out;
		out = new Object[maxx - minx][maxy - miny];
		for (int x = minx; x < maxx; x += 1) {
			for (int y = miny; y < maxy; y += 1) {

				out[x - minx][y - miny] = source[x][y];

			}
		}
		return out;

	}

	private Element[][] getView(Element[][] source, int minx, int maxx,
			int miny, int maxy) {
		Element[][] out;
		out = new Element[maxx - minx + 1][maxy - miny + 1];
		for (int x = minx; x <= maxx; x += 1) {
			for (int y = miny; y <= maxy; y += 1) {

				out[x - minx][y - miny] = (inRange(x, 0, size) && inRange(y, 0,
						size)) ? source[x][y] : null;

			}
		}
		return out;

	}
}
