public class App {
    public static void main(String[] args) {
        FileNavigator fileNavigator = new FileNavigator();
        fileNavigator.add(new FileData("file1", 66, "c:/games"));
        fileNavigator.add(new FileData("file2", 33, "c:/games"));
        fileNavigator.add(new FileData("file3", 88, "d:/download"));
        fileNavigator.add(new FileData("file4", 77, "c:/games"));
        fileNavigator.add(new FileData("file5", 55, "d:/download"));
        fileNavigator.add(new FileData("file6", 11, "d:/download"));
        fileNavigator.add(new FileData("file7", 11, "f:/pics"));
        fileNavigator.add(new FileData("file8", 22, "f:/pics"));
        fileNavigator.add(new FileData("file9", 99, "d:/download"));
        fileNavigator.add(new FileData("file10", 0, "d:/download"));

        System.out.println(("Find: " + fileNavigator.find("c:/games")));

        System.out.println("Filter: " + fileNavigator.filterBySize(67));

        fileNavigator.remove("c:/games");

        System.out.println("sort by size: "+fileNavigator.sortBySize().toString());

        System.out.println("all map: "+fileNavigator);

        System.out.println();
    }
}
