package com.sbu.webspotify.dto;

public class BrowsingMode {

    private boolean isPrivateSession;

    public BrowsingMode(boolean isPrivateSession) {
        this.isPrivateSession = isPrivateSession;
    }

	/**
	 * @return the isPrivateSession
	 */
	public boolean isPrivateSession() {
		return isPrivateSession;
	}

	/**
	 * @param isPrivateSession the isPrivateSession to set
	 */
	public void setIsPrivateSession(boolean isPrivateSession) {
		this.isPrivateSession = isPrivateSession;
	}

}