package city.olooe.plogging_project.persistence.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import city.olooe.plogging_project.model.map.MapEntity;
import city.olooe.plogging_project.model.map.StopoverEntity;


@SpringBootTest
public class MapDataParse {
  @Autowired
  private StopoverRepository stopoverRepository;

  @Autowired
  private MapRepository mapRepository;

  private List<MapEntity> mapEntities = new ArrayList<>();
  private List<StopoverEntity> stopoverEntities = new ArrayList<>();

  @BeforeEach
  public void 파일을_MapEntityList로전환() throws Exception {
    File file = new File("C:\\workspaces\\Plogging_Project_Backend\\src\\main\\java\\city\\olooe\\plogging_project\\Recommended_route.csv");
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ms949"));
    String str = null;
    int idx = 1;
    while ((str = br.readLine()) != null) {
      String[] strs = str.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
      ArrayList<String> list = new ArrayList<>(Arrays.asList(strs));
      // System.out.println(str);
      String pkTime = list.get(1);
      String courseDetail = list.get(6);
      String courseName = list.get(5);
      String distance = list.get(3);
      distance = distance.replaceAll("km", "");
      distance = distance.replaceAll("㎞", "");
      distance = distance.replaceAll("Km", "");

      String time = list.get(4);
      String addr = list.get(2);
      addr = addr.replaceAll("\"", "");

      double x = Double.parseDouble(list.get(8));
      double y = Double.parseDouble(list.get(9));
      // break;
      MapEntity m = MapEntity.builder()
      //임시키 삭제해서 아래 코드 작동 x
          // .tmp(pkTime)
          .courseDetail(courseDetail)
          .courseName(courseName)
          .distance(Double.parseDouble(distance))
          .time(Integer.parseInt(time))
          .addr(addr)
          .build();
//임시키 삭제해서 아래 코드 작동 x
      // if (mapEntities.isEmpty() || !mapEntities.get(mapEntities.size() - 1).getTmp().equals(pkTime)) {
      //   idx = 1;
      // }
      StopoverEntity s = StopoverEntity.builder()
      //임시키 삭제해서 아래 코드 작동 x
          //.tmp(pkTime)
          .stopoverIdx(idx++)
          .stopoverX(x)
          .stopoverY(y)
          // .mapEntity(m)
          .build();
      mapEntities.add(m);
      stopoverEntities.add(s);
      // System.out.println(m);
    }
    br.close();
  }

  @Test
  public void test_파일_불러오기_테스트() throws IOException {
    List<MapEntity> inputList = mapEntities.stream().distinct().collect(Collectors.toList());

    mapRepository.saveAll(inputList);

  }

  @Transactional
  @Test
  @Rollback(false)
  public void test_파일에서_stopover_적용() {
    // stopoverEntities.forEach(s -> System.out.println(s));
    List<MapEntity> maps = mapRepository.findAll(); // pk가 있어야함

    for (StopoverEntity se : stopoverEntities) {
      for (MapEntity me : maps) {
        //임시키 삭제해서 아래 코드 작동 x
        // if (se.getTmp().equals(me.getTmp())) {
        //   se.setMapEntity(me);
        //   break;
        // } 
      }
    }
    stopoverRepository.saveAll(stopoverEntities);
  }

  // @Test
  // public void test_findByTmp() {
  //   System.out.println(mapRepository.findByTmp("00:33.0"));
  // }

}
