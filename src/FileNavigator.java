import java.util.ArrayList;
import java.util.List;

public class FileNavigator {
    private final List<Bucket> table;

    public FileNavigator() {
        table = new ArrayList<>();
    }

    public void add(FileData fileData) {
        FileNameSize file = new FileNameSize(fileData);
        if (!table.isEmpty()) {
            boolean pathContains = false;
            for (Bucket bucketTemp : table) {
                if (bucketTemp.path.equals(fileData.getFilePath())) {
                    bucketTemp.bucket.add(file);
                    pathContains = true;
                    System.out.println("add " + fileData.getFileName() + " to exist " + fileData.getFilePath());
                    break;
                }
            }
            if (!pathContains) {
                Bucket bucketNew = new Bucket(fileData);
                bucketNew.bucket.add(file);
                table.add(bucketNew);
                System.out.println("add " + fileData.getFileName() + " to empty " + fileData.getFilePath());
            }
        } else {
            Bucket bucketNew = new Bucket(fileData);
            bucketNew.bucket.add(file);
            table.add(bucketNew);
            System.out.println("add " + fileData.getFileName() + " to new list " + fileData.getFilePath());
        }
    }

    public List<FileNameSize> find(String path) {
        for (Bucket bucketTemp : table) {
            if (bucketTemp.path.equals(path)) {
                return bucketTemp.bucket;
            }
        }
        return null;
    }

    public List<FileNameSize> filterBySize(int size) {
        List<FileNameSize> filterList = new ArrayList<>();
        for (Bucket bucketTemp : table) {
            for (FileNameSize file : bucketTemp.bucket) {
                if (file.size <= size) {
                    filterList.add(file);
                }
            }
        }
        return filterList;
    }

    public void remove(String path) {
        for (Bucket bucketTemp : table) {
            if (bucketTemp.path.equals(path)) {
                table.remove(bucketTemp);
                break;
            }
        }
    }

    public List<FileData> sortBySize() {
        ArrayList<FileData> sortList = new ArrayList<>();
        for (Bucket bucketTemp : table) {
            for (FileNameSize file : bucketTemp.bucket) {
                FileData fileData = new FileData(file.name, file.size, bucketTemp.path);
                sortList.add(fileData);
            }
        }
        sortList.sort((o1, o2) -> {
            if (o1.getFileSize() > o2.getFileSize()) {
                return 1;
            } else if (o1.getFileSize() < o2.getFileSize()) {
                return -1;
            } else {
                return 0;
            }
        });
        return sortList;
    }


    static class FileNameSize {
        String name;
        int size;

        public FileNameSize(FileData fileData) {
            this.name = fileData.getFileName();
            this.size = fileData.getFileSize();
        }

        @Override
        public String toString() {
            return " [" + name + " " + size + "] ";
        }
    }

    static class Bucket {
        String path;
        List<FileNameSize> bucket;

        public Bucket(FileData fileData) {
            this.path = fileData.getFilePath();
            bucket = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "[" + bucket + "]";
        }
    }

    @Override
    public String toString() {
        return " " + table + " ";
    }
}
