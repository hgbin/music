package music;

import java.util.Date;

public class Music {
	int musicid; // int(0) NOT NULL,
	String musicname; // varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT
						// NULL COMMENT '��������',
	String musicsinger; // varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT
						// NULL COMMENT '�ݳ���',
	String musicurl; // varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT
						// NULL COMMENT '��������',
	String musicDatetime; // datetime(0) NULL DEFAULT NULL COMMENT '����ʱ��',
	String musicType; // varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT
						// NULL COMMENT '��������',
	String musicbackurl; // varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT
							// NULL COMMENT '��������',
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
