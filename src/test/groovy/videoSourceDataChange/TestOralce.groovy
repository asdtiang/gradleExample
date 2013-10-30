package videoSourceDataChange

import java.sql.Connection
import java.sql.DriverManager
import javax.sql.DataSource
import groovy.sql.Sql
import oracle.jdbc.driver.OracleTypes

driver = oracle.jdbc.driver.OracleDriver
Connection conn = DriverManager.getConnection(
    'jdbc:oracle:thin:video_user/video_pw@localhost:1521:orcl');

/*
 *
 * Here we call a procedural block with a closure.
 * ${Sql.INTEGER} and ${Sql.VARCHAR} are out parameters
 * which are passed to the closure.
 *
 */
Sql sql = new Sql(conn);
sql.eachRow("""select *
from  
( select video.inserttime,video.updatetime,video.programtype,video.programurl,video.programname
,web.website_name,web.base_url
  from IVAM_web_site web,ivam_video video where video.websiteid = web.id 
  order by video.updatetime desc ) 
where ROWNUM >=0 and rownum<5"""){
    println it
}









