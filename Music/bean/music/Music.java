package music;

import java.util.Date;

public class Music {
	int musicid; // int(0) NOT NULL,
	String musicname; // varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT
						// NULL COMMENT '歌曲名称',
	String musicsinger; // varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT
						// NULL COMMENT '演唱者',
	String musicurl; // varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT
						// NULL COMMENT '歌曲链接',
	String musicDatetime; // datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
	String musicType; // varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT
						// NULL COMMENT '歌曲类型',
	String musicbackurl; // varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT
							// NULL COMMENT '歌曲背景',
	String musicoutlink;
	public int getMusicid() {
		return musicid;
	}
	public void setMusicid(int musicid) {
		this.musicid = musicid;
	}
	public String getMusicname() {
		return musicname;
	}
	public void setMusicname(String musicname) {
		this.musicname = musicname;
	}
	public String getMusicsinger() {
		return musicsinger;
	}
	public void setMusicsinger(String musicsinger) {
		this.musicsinger = musicsinger;
	}
	public String getMusicurl() {
		return musicurl;
	}
	public void setMusicurl(String musicurl) {
		this.musicurl = musicurl;
	}
	public String getMusicDatetime() {
		return musicDatetime;
	}
	public void setMusicDatetime(String musicDatetime) {
		this.musicDatetime = musicDatetime;
	}
	public String getMusicType() {
		return musicType;
	}
	public void setMusicType(String musicType) {
		this.musicType = musicType;
	}
	public String getMusicbackurl() {
		return musicbackurl;
	}
	public void setMusicbackurl(String musicbackurl) {
		this.musicbackurl = musicbackurl;
	}
	public String getMusicoutlink() {
		return musicoutlink;
	}
	public void setMusicoutlink(String musicoutlink) {
		this.musicoutlink = musicoutlink;
	}
}
