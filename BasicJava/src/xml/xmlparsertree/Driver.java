package xml.xmlparsertree;

public class Driver {

    public static void main(String[] args) {

        XMLTree tree = new XMLTree("C:\\Users\\shilpa_jain\\git\\ds\\XMLParsers\\XMLParsers\\src\\jaxb\\input.xml");

//        System.out.println(tree.root.data);
//
//        for (XMLTree.Node node: tree.root.childNodes) {
//            System.out.println("NODE: " + node.data);
//
//            if (node.childNodes != null) {
//                for (XMLTree.Node child: node.childNodes) {
//                    System.out.println("\t" + "NODE: " + child.data);
//                }
//            }
//        }
//
//        System.out.println(tree.root.childNodes);

        tree.printDfsTraversal();

    }

}