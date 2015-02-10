public class Main {

	public static void main(String args[]) throws Exception {
		Model Model = new Model();

		Part prts = new Part("Part1", "Name1", "Vendor 1", 1);
		Part prts1 = new Part("Part2", "Name2", "Vendor 2", 2);
		Part prts2 = new Part("Part3", "Name3", "Vendor 3", 3);
		Model.addPart(prts);
		Model.addPart(prts1);
		Model.addPart(prts2);


		View View = new View(Model);
		Controller Controller = new Controller(Model, View);
		View.register(Controller);
	}
}