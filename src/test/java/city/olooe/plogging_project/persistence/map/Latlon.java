package city.olooe.plogging_project.persistence.map;

import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;


/**
* @author : 이시화
* @date: 23.06.09
* 
* @brief: GRS80TM 좌표를 WGS84변환하기 위한 클래스 -> db저장용
*/
@SpringBootTest
@Slf4j
public class Latlon {
  String grs80TMParams = "+proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=600000 +ellps=GRS80 +units=m +no_defs";
  String wgs84Params = "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs";
  
  // // Create a CRS factory
  // CRSFactory crsFactory = new CRSFactory();
  
  // // Define the GRS80TM and WGS84 coordinate reference systems
  // CoordinateReferenceSystem grs80TMCRS = crsFactory.createFromParameters(null, grs80TMParams);
  // CoordinateReferenceSystem wgs84CRS = crsFactory.createFromParameters(null, wgs84Params);
  
  // // Create a coordinate transform from GRS80TM to WGS84
  // CoordinateTransform transform = new CoordinateTransform(grs80TMCRS, wgs84CRS);
  
  // // Define the GRS80TM coordinates
  // double x = 198975.7;
  // double y = 553421.7;
  
  // // Create a ProjCoordinate object for the input GRS80TM coordinates
  // ProjCoordinate grs80TMCoordinate = new ProjCoordinate(x, y);
  
  // // Transform the GRS80TM coordinates to WGS84
  // ProjCoordinate wgs84Coordinate = new ProjCoordinate();
  // transform.transform(grs80TMCoordinate, wgs84Coordinate);
  
  // // Access the transformed WGS84 coordinates
  // double transformedLon = wgs84Coordinate.x;
  // double transformedLat = wgs84Coordinate.y;
  
  // System.out.println("Transformed coordinates: " + transformedLon + ", " + transformedLat);
  
}