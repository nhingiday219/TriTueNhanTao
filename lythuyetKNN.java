import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class dulieuhoa {
    double chieucao;
    double chieurong;
    int loaihoa;

    public dulieuhoa(double chieucao, double chieurong, int loaihoa) {
        this.chieucao = chieucao;
        this.chieurong = chieurong;
        this.loaihoa = loaihoa;
    }
}
public class lythuyetKNN {
    private static double khoangcacheucludean(dulieuhoa a, dulieuhoa b) {
        return Math.sqrt(Math.pow(a.chieucao - b.chieucao, 2) + Math.pow(a.chieurong - b.chieurong, 2));
    }
    public static int loaihoa(dulieuhoa[] trainingData, dulieuhoa newFlower, int k) {
        List<FlowerDistance> khoangcach = new ArrayList<>();

        for (dulieuhoa flower : trainingData) {
            double distance = khoangcacheucludean(flower, newFlower);
            khoangcach.add(new FlowerDistance(flower, distance));
        }

        khoangcach.sort(Comparator.comparingDouble(fd -> fd.tinhkhoangcach));

        int[] dem = new int[2];
        for (int i = 0; i < k; i++) {
            dem[khoangcach.get(i).Hoa.loaihoa]++;
        }
        return (dem[1] > dem[0]) ? 1 : 0;
    }

    static class FlowerDistance {
        dulieuhoa Hoa;
        double tinhkhoangcach;

        FlowerDistance(dulieuhoa flower, double distance) {
            this.Hoa = flower;
            this.tinhkhoangcach = distance;
        }
    }

    public static void main(String[] args) {
        dulieuhoa[] trainingData = {
            new dulieuhoa(5, 3, 1),    
            new dulieuhoa(6, 2.5, 1),  
            new dulieuhoa(4, 4, 0),    
            new dulieuhoa(5.5, 3.5, 1),
            new dulieuhoa(4.5, 4.5, 0) 
        };

     
        dulieuhoa diemtest = new dulieuhoa(5.1, 3.2, -1);

        int k = 3;

        int lophoamoi = loaihoa(trainingData, diemtest, k);
        System.out.println("Điểm mới được phân loại là: " + (lophoamoi == 1 ? "Hoa hồng" : "Hoa cúc"));
    }
}
