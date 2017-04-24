package online.themixhub.demo.logic;

public enum Stage {
	
	ENGINEER_WAITING_TO_CLAIM(0,"Waiting for an engineer to claim"),
	ENGINEER_CLAIMED_WAITING_REVIEW(1,"Claimed by engineer, waiting for engineer to review"),
	USER_WAITING_RESPONSE(2,"Waiting on user-response"),
	ENGINEER_TIER_2_WAITING(3,"Second Tier - Waiting for engineer"),
	USER_TIER_2_WAITING_RESPONSE(4,"Second Tier - Waiting on user-response"),
	ENGINEER_TIER_3_WAITING(5,"Third Tier (Final) - Waiting for engineer"),
	ENGINEER_JOB_FINISHED(6,"Job Finished, set by engineer"),
	USER_JOB_FINISHED(7,"Job Finished, set by user"),
	UNKNOWN_STAGE(8,"Unknown stage!");

	Stage(int id, String description){
		this.id = id;
		this.description = description;	
	}
		
	private int id;
	private String description;
	
	public int getID(){
		return id;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String toString(){
		return getID()+":"+getDescription();
	}
	
	public static Stage getStageFromID(int stageID) {
		switch (stageID) {
			case 0:
				return Stage.ENGINEER_WAITING_TO_CLAIM;

			case 1:
				return Stage.ENGINEER_CLAIMED_WAITING_REVIEW;

			case 2:
				return Stage.USER_WAITING_RESPONSE;

			case 3:
				return Stage.ENGINEER_TIER_2_WAITING;

			case 4:
				return Stage.USER_TIER_2_WAITING_RESPONSE;

			case 5:
				return Stage.ENGINEER_TIER_3_WAITING;

			case 6:
				return Stage.ENGINEER_JOB_FINISHED;

			case 7:
				return Stage.USER_JOB_FINISHED;

			case 8:
				return Stage.UNKNOWN_STAGE;

			default:
				return Stage.UNKNOWN_STAGE;
		}
	}
	

}
