package com.trainoo.novel.chapterParser;

public class TitleInfo {

	/**
	 * 章节下下标
	 */
	private int index;
	/**
	 * 章节标题
	 */
	private String title;
	/**
	 * 章节开始字节数，用来和RandomAccessFile作章节跳转和单章解析用
	 */
	private int startLength;

	public TitleInfo() {
	}

	public TitleInfo(int index, String title, int startLength) {
		this.index = index;
		this.title = title;
		this.startLength = startLength;
	}

	public int getIndex() {
		return index;
	}

	public String getTitle() {
		return title;
	}

	public long getStartLength() {
		return startLength;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setStartLength(int startLength) {
		this.startLength = startLength;
	}

	public String toString() {
		return "[index = " + index + ",title = " + title + ",startLength = " + startLength + "]";
	}
}