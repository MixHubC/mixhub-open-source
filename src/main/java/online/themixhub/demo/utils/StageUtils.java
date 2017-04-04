package online.themixhub.demo.utils;

/**
 * Stage AKA Status
 *
 * @author The Mix Hub Online
 */
public class StageUtils {

	public static String stageToString(int stage) {
		if(stage == 0) {
			return "Waiting for an engineer to claim";
		} else if(stage == 1) {
			return "Claimed by engineer, waiting for engineer to review";
		} else if(stage == 2) {
			return "Waiting on user-response";
		} else if(stage == 3) {
			return "Second Tier - Waiting for engineer";
		} else if(stage == 4) {
			return "Second Tier - Waiting on user-response";
		} else if(stage == 5) {
			return "Third Tier (Final) - Waiting for engineer";
		} else if(stage == 98) {
			return "Job Finished, set by engineer";
		}  else if(stage == 99) {
			return "Job Finished, set by user";
		} else {
			return "Unknown stage!";
		}
	}

}
