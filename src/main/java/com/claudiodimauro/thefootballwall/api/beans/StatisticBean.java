package com.claudiodimauro.thefootballwall.api.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class StatisticBean {
	private int acceleration;
	private int sprintSpeed;
	private int positioning;
	private int finishing;
	private int shotPower;
	private int longShots;
	private int volleys;
	private int penalties;
	private int vision;
	private int crossing;
	private int freeKickAccuracy;
	private int shortPassing;
	private int longPassing;
	private int curve;
	private int agility;
	private int balance;
	private int reactions;
	private int ballControl;
	private int dribbling;
	private int composure;
	private int interceptions;
	private int headingAccuracy;
	private int marking;
	private int standingTackle;
	private int slidingTackle;
	private int jumping;
	private int stamina;
	private int strength;
	private int aggression;
	
	/*
	 *	Setting up a default value of 50, if the passed value is out the [1,100] range or if
	 *	the value is null.
	 *	The value could be null because this class is growed up using the builder pattern. 
	 */
	public StatisticBean setAcceleration(Integer acceleration) {
		if(acceleration < 1 || acceleration > 100 || acceleration == null) {
			this.acceleration = 50;
		} else {
			this.acceleration = acceleration.intValue();
		}
		return this;
	}
	
	public StatisticBean setSprintSpeed(Integer sprintSpeed) {
		if(sprintSpeed < 1 || sprintSpeed > 100 || sprintSpeed == null) {
			this.sprintSpeed = 50;
		} else {
			this.sprintSpeed = sprintSpeed.intValue();
		}
		return this;
	}
	
	public StatisticBean setPositioning(Integer positioning) {
		if(positioning < 1 || positioning > 100 || positioning == null) {
			this.positioning = 50;
		} else {
			this.positioning = positioning.intValue();
		}
		return this;
	}
	
	public StatisticBean setFinishing(Integer finishing) {
		if(finishing < 1 || finishing > 100 || finishing == null) {
			this.finishing = 50;
		} else {
			this.finishing = finishing.intValue();
		}
		return this;
	}
	
	public StatisticBean setShotPower(Integer shotPower) {
		if(shotPower < 1 || shotPower > 100 || shotPower == null) {
			this.shotPower = 50;
		} else {
			this.shotPower = shotPower.intValue();
		}
		return this;
	}
	
	public StatisticBean setLongShots(Integer longShots) {
		if(longShots < 1 || longShots > 100 || longShots == null) {
			this.longShots = 50;
		} else {
			this.longShots = longShots.intValue();
		}
		return this;
	}
	
	public StatisticBean setVolleys(Integer volleys) {
		if(volleys < 1 || volleys > 100 || volleys == null) {
			this.volleys = 50;
		} else {
			this.volleys = volleys.intValue();
		}
		return this;
	}
	
	public StatisticBean setPenalties(Integer penalties) {
		if(penalties < 1 || penalties > 100 || penalties == null) {
			this.penalties = 50;
		} else {
			this.penalties = penalties.intValue();
		}
		return this;
	}
	
	public StatisticBean setVision(Integer vision) {
		if(vision < 1 || vision > 100 || vision == null) {
			this.vision = 50;
		} else {
			this.vision = vision.intValue();
		}
		return this;
	}
	
	public StatisticBean setCrossing(Integer crossing) {
		if(crossing < 1 || crossing > 100 || crossing == null) {
			this.crossing = 50;
		} else {
			this.crossing = crossing.intValue();
		}
		return this;
	}
}
