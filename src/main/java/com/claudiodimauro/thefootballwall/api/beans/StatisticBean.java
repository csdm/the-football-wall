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
	
	public StatisticBean setFreeKickAccuracy(Integer freeKickAccuracy) {
		if(freeKickAccuracy < 1 || freeKickAccuracy > 100 || freeKickAccuracy == null) {
			this.freeKickAccuracy = 50;
		} else {
			this.freeKickAccuracy = freeKickAccuracy.intValue();
		}
		return this;
	}
	
	public StatisticBean setShortPassing(Integer shortPassing) {
		if(shortPassing < 1 || shortPassing > 100 || shortPassing == null) {
			this.shortPassing = 50;
		} else {
			this.shortPassing = shortPassing.intValue();
		}
		return this;
	}
	
	public StatisticBean setLongPassing(Integer longPassing) {
		if(longPassing < 1 || longPassing > 100 || longPassing == null) {
			this.longPassing = 50;
		} else {
			this.longPassing = longPassing.intValue();
		}
		return this;
	}
	
	public StatisticBean setCurve(Integer curve) {
		if(curve < 1 || curve > 100 || curve == null) {
			this.curve = 50;
		} else {
			this.curve = curve.intValue();
		}
		return this;
	}
	
	public StatisticBean setAgility(Integer agility) {
		if(agility < 1 || agility > 100 || agility == null) {
			this.agility = 50;
		} else {
			this.agility = agility.intValue();
		}
		return this;
	}
	
	public StatisticBean setBalance(Integer balance) {
		if(balance < 1 || balance > 100 || balance == null) {
			this.agility = 50;
		} else {
			this.agility = balance.intValue();
		}
		return this;
	}
	
	public StatisticBean setReactions(Integer reactions) {
		if(reactions < 1 || reactions > 100 || reactions == null) {
			this.reactions = 50;
		} else {
			this.reactions = reactions.intValue();
		}
		return this;
	}
	
	public StatisticBean setBallControl(Integer ballControl) {
		if(ballControl < 1 || ballControl > 100 || ballControl == null) {
			this.ballControl = 50;
		} else {
			this.ballControl = ballControl.intValue();
		}
		return this;
	}
	
	public StatisticBean setComposure(Integer composure) {
		if(composure < 1 || composure > 100 || composure == null) {
			this.composure = 50;
		} else {
			this.composure = composure.intValue();
		}
		return this;
	}
	
	
}
