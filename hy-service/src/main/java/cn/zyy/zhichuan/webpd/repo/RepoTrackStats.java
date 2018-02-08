package cn.zyy.zhichuan.webpd.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cn.zyy.zhichuan.webpd.domain.TrackStat;

public interface RepoTrackStats extends JpaRepository<TrackStat, Integer>
{

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO track_stats (ts, uid, adid, mid, aid, impress, click) VALUES (date_format(now(), '%y-%m-%d %H'),?1,?2,?3,?4,?5,?6) ON DUPLICATE KEY UPDATE impress=impress+?5, click=click+?6", nativeQuery = true)
    void trackUpdate(Integer uid, Integer adid, Integer mid, Integer articleId, long impress, long click);

    @Query(value = "SELECT SUM(impress) FROM track_stats WHERE adid = ?1", nativeQuery = true)
    long sumImpressByAdCode(Integer adid);

    // page daily

    @Query(value = "SELECT *, 0 as id FROM ( SELECT t.ts, SUM(t.impress) AS impress, SUM(t.click) AS click FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND ts >= ?2 AND ts < ?3 ) AS t GROUP BY t.ts ORDER BY t.ts ASC ) AS tmp \n#pageable\n", countQuery = "SELECT COUNT(tmp.ts) FROM ( SELECT t.ts FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND ts >= ?2 AND ts < ?3 ) AS t GROUP BY t.ts ORDER BY t.ts DESC ) AS tmp", nativeQuery = true)
    Page<Object[]> sumByUserIdAndTsBetweenDailyPageable(Integer uid, String beginDate, String endDate, Pageable page);

    @Query(value = "SELECT *, 0 as id FROM ( SELECT t.ts, SUM(t.impress) AS impress, SUM(t.click) AS click FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND mid = ?2 AND ts >= ?3 AND ts < ?4 ) AS t GROUP BY t.ts ORDER BY t.ts ASC ) AS tmp \n#pageable\n", countQuery = "SELECT COUNT(tmp.ts) FROM ( SELECT t.ts FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND mid = ?2 AND ts >= ?3 AND ts < ?4 ) AS t GROUP BY t.ts ORDER BY t.ts DESC ) AS tmp", nativeQuery = true)
    Page<Object[]> sumByUserIdAndMidAndTsBetweenDailyPageable(Integer uid, Integer materialId, String beginDate, String endDate, Pageable page);

    @Query(value = "SELECT *, 0 as id FROM ( SELECT t.ts, SUM(t.impress) AS impress, SUM(t.click) AS click FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND adid = ?2 AND ts >= ?3 AND ts < ?4 ) AS t GROUP BY t.ts ORDER BY t.ts ASC ) AS tmp \n#pageable\n", countQuery = "SELECT COUNT(tmp.ts) FROM ( SELECT t.ts FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND adid = ?2 AND ts >= ?3 AND ts < ?4 ) AS t GROUP BY t.ts ORDER BY t.ts DESC ) AS tmp", nativeQuery = true)
    Page<Object[]> sumByUserIdAndIdAndTsBetweenDailyPageable(Integer uid, Integer id, String beginDate, String endDate, Pageable page);

    @Query(value = "SELECT *, 0 as id FROM ( SELECT t.ts, SUM(t.impress) AS impress, SUM(t.click) AS click FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND mid = ?2 AND adid = ?3 AND ts >= ?4 AND ts < ?5 ) AS t GROUP BY t.ts ORDER BY t.ts ASC ) AS tmp \n#pageable\n", countQuery = "SELECT COUNT(tmp.ts) FROM ( SELECT t.ts FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND mid = ?2 AND adid = ?3 AND ts >= ?4 AND ts < ?5 ) AS t GROUP BY t.ts ORDER BY t.ts DESC ) AS tmp", nativeQuery = true)
    Page<Object[]> sumByUserIdAndMidAndIdAndTsBetweenDailyPageable(Integer uid, Integer materialId, Integer id, String beginDate, String endDate, Pageable page);

    // page hourly

    @Query(value = "SELECT *, 0 as id FROM ( SELECT t.ts, SUM(t.impress) AS impress, SUM(t.click) AS click FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d %H') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND ts >= ?2 AND ts < ?3 ) AS t GROUP BY t.ts ORDER BY t.ts ASC ) AS tmp \n#pageable\n", countQuery = "SELECT COUNT(tmp.ts) FROM ( SELECT t.ts FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d %H') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND ts >= ?2 AND ts < ?3 ) AS t GROUP BY t.ts ORDER BY t.ts DESC ) AS tmp", nativeQuery = true)
    Page<Object[]> sumByUserIdAndTsBetweenHourlyPageable(Integer uid, String date, String endDate, Pageable page);

    @Query(value = "SELECT *, 0 as id FROM ( SELECT t.ts, SUM(t.impress) AS impress, SUM(t.click) AS click FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d %H') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND mid = ?2 AND ts >= ?3 AND ts < ?4 ) AS t GROUP BY t.ts ORDER BY t.ts ASC ) AS tmp \n#pageable\n", countQuery = "SELECT COUNT(tmp.ts) FROM ( SELECT t.ts FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d %H') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND mid = ?2 AND ts >= ?3 AND ts < ?4 ) AS t GROUP BY t.ts ORDER BY t.ts DESC ) AS tmp", nativeQuery = true)
    Page<Object[]> sumByUserIdAndMidAndTsBetweenHourlyPageable(Integer uid, Integer materialId, String beginDate, String endDate, Pageable page);

    @Query(value = "SELECT *, 0 as id FROM ( SELECT t.ts, SUM(t.impress) AS impress, SUM(t.click) AS click FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d %H') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND adid = ?2 AND ts >= ?3 AND ts < ?4 ) AS t GROUP BY t.ts ORDER BY t.ts ASC ) AS tmp \n#pageable\n", countQuery = "SELECT COUNT(tmp.ts) FROM ( SELECT t.ts FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d %H') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND adid = ?2 AND ts >= ?3 AND ts < ?4 ) AS t GROUP BY t.ts ORDER BY t.ts DESC ) AS tmp", nativeQuery = true)
    Page<Object[]> sumByUserIdAndIdAndTsBetweenHourlyPageable(Integer uid, Integer id, String beginDate, String endDate, Pageable page);

    @Query(value = "SELECT *, 0 as id FROM ( SELECT t.ts, SUM(t.impress) AS impress, SUM(t.click) AS click FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d %H') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND mid = ?2 AND adid = ?3 AND ts >= ?4 AND ts < ?5 ) AS t GROUP BY t.ts ORDER BY t.ts ASC ) AS tmp \n#pageable\n", countQuery = "SELECT COUNT(tmp.ts) FROM ( SELECT t.ts FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d %H') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND mid = ?2 AND adid = ?3 AND ts >= ?4 AND ts < ?5 ) AS t GROUP BY t.ts ORDER BY t.ts DESC ) AS tmp", nativeQuery = true)
    Page<Object[]> sumByUserIdAndMidAndIdAndTsBetweenHourlyPageable(Integer uid, Integer materialId, Integer id, String beginDate, String endDate, Pageable page);

    // all daily

    @Query(value = "SELECT * FROM ( SELECT t.ts, SUM(t.impress) AS impress, SUM(t.click) AS click FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND ts >= ?2 AND ts < ?3 ) AS t GROUP BY t.ts ORDER BY t.ts ASC ) AS tmp", nativeQuery = true)
    List<Object[]> sumByUserIdAndTsBetweenDaily(Integer uid, String beginDate, String endDate);

    @Query(value = "SELECT * FROM ( SELECT t.ts, SUM(t.impress) AS impress, SUM(t.click) AS click FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND mid = ?2 AND ts >= ?3 AND ts < ?4 ) AS t GROUP BY t.ts ORDER BY t.ts ASC ) AS tmp", nativeQuery = true)
    List<Object[]> sumByUserIdAndMidAndTsBetweenDaily(Integer uid, Integer materialId, String beginDate, String endDate);

    @Query(value = "SELECT * FROM ( SELECT t.ts, SUM(t.impress) AS impress, SUM(t.click) AS click FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND adid = ?2 AND ts >= ?3 AND ts < ?4 ) AS t GROUP BY t.ts ORDER BY t.ts ASC ) AS tmp", nativeQuery = true)
    List<Object[]> sumByUserIdAndIdAndTsBetweenDaily(Integer uid, Integer id, String beginDate, String endDate);

    @Query(value = "SELECT * FROM ( SELECT t.ts, SUM(t.impress) AS impress, SUM(t.click) AS click FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND mid = ?2 AND adid = ?3 AND ts >= ?4 AND ts < ?5 ) AS t GROUP BY t.ts ORDER BY t.ts ASC ) AS tmp", nativeQuery = true)
    List<Object[]> sumByUserIdAndMidAndIdAndTsBetweenDaily(Integer uid, Integer materialId, Integer id, String beginDate, String endDate);

    // all hourly

    @Query(value = "SELECT * FROM ( SELECT t.ts, SUM(t.impress) AS impress, SUM(t.click) AS click FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d %H') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND ts >= ?2 AND ts < ?3 ) AS t GROUP BY t.ts ORDER BY t.ts ASC ) AS tmp", nativeQuery = true)
    List<Object[]> sumByUserIdAndTsBetweenHourly(Integer uid, String beginDate, String endDate);

    @Query(value = "SELECT * FROM ( SELECT t.ts, SUM(t.impress) AS impress, SUM(t.click) AS click FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d %H') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND mid = ?2 AND ts >= ?3 AND ts < ?4 ) AS t GROUP BY t.ts ORDER BY t.ts ASC ) AS tmp", nativeQuery = true)
    List<Object[]> sumByUserIdAndMidAndTsBetweenHourly(Integer uid, Integer materialId, String beginDate, String endDate);

    @Query(value = "SELECT * FROM ( SELECT t.ts, SUM(t.impress) AS impress, SUM(t.click) AS click FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d %H') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND adid = ?2 AND ts >= ?3 AND ts < ?4 ) AS t GROUP BY t.ts ORDER BY t.ts ASC ) AS tmp", nativeQuery = true)
    List<Object[]> sumByUserIdAndIdAndTsBetweenHourly(Integer uid, Integer id, String beginDate, String endDate);

    @Query(value = "SELECT * FROM ( SELECT t.ts, SUM(t.impress) AS impress, SUM(t.click) AS click FROM ( SELECT DATE_FORMAT(ts, '%Y-%m-%d %H') AS ts, impress, click FROM track_stats WHERE uid = ?1 AND mid = ?2 AND adid = ?3 AND ts >= ?4 AND ts < ?5 ) AS t GROUP BY t.ts ORDER BY t.ts ASC ) AS tmp", nativeQuery = true)
    List<Object[]> sumByUserIdAndMidAndIdAndTsBetweenHourly(Integer uid, Integer materialId, Integer id, String beginDate, String endDate);
}
