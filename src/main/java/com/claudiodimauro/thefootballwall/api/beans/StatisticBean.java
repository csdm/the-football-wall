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
		if(acceleration == null || acceleration > 100 || acceleration < 1) {
			this.acceleration = 50;
		} else {
			this.acceleration = acceleration.intValue();
		}
		return this;
	}
	
	public StatisticBean setSprintSpeed(Integer sprintSpeed) {
		if(sprintSpeed == null || sprintSpeed > 100 || sprintSpeed < 1) {
			this.sprintSpeed = 50;
		} else {
			this.sprintSpeed = sprintSpeed.intValue();
		}
		return this;
	}
	
	public StatisticBean setPositioning(Integer positioning) {
		if(positioning == null || positioning > 100 || positioning < 1) {
			this.positioning = 50;
		} else {
			this.positioning = positioning.intValue();
		}
		return this;
	}
	
	public StatisticBean setFinishing(Integer finishing) {
		if(finishing == null || finishing > 100 || finishing < 1) {
			this.finishing = 50;
		} else {
			this.finishing = finishing.intValue();
		}
		return this;
	}
	
	public StatisticBean setShotPower(Integer shotPower) {
		if(shotPower == null || shotPower > 100 || shotPower < 1) {
			this.shotPower = 50;
		} else {
			this.shotPower = shotPower.intValue();
		}
		return this;
	}
	
	public StatisticBean setLongShots(Integer longShots) {
		if(longShots == null || longShots > 100 || longShots < 1) {
			this.longShots = 50;
		} else {
			this.longShots = longShots.intValue();
		}
		return this;
	}
	
	public StatisticBean setVolleys(Integer volleys) {
		if(volleys == null || volleys > 100 || volleys < 1) {
			this.volleys = 50;
		} else {
			this.volleys = volleys.intValue();
		}
		return this;
	}
	
	public StatisticBean setPenalties(Integer penalties) {
		if(penalties == null || penalties > 100 || penalties < 1) {
			this.penalties = 50;
		} else {
			this.penalties = penalties.intValue();
		}
		return this;
	}
	
	public StatisticBean setVision(Integer vision) {
		if(vision == null || vision > 100 || vision < 1) {
			this.vision = 50;
		} else {
			this.vision = vision.intValue();
		}
		return this;
	}
	
	public StatisticBean setCrossing(Integer crossing) {
		if(crossing == null || crossing > 100 || crossing < 1) {
			this.crossing = 50;
		} else {
			this.crossing = crossing.intValue();
		}
		return this;
	}
	
	public StatisticBean setFreeKickAccuracy(Integer freeKickAccuracy) {
		if(freeKickAccuracy == null || freeKickAccuracy > 100 || freeKickAccuracy < 1) {
			this.freeKickAccuracy = 50;
		} else {
			this.freeKickAccuracy = freeKickAccuracy.intValue();
		}
		return this;
	}
	
	public StatisticBean setShortPassing(Integer shortPassing) {
		if(shortPassing == null || shortPassing > 100 || shortPassing < 1) {
			this.shortPassing = 50;
		} else {
			this.shortPassing = shortPassing.intValue();
		}
		return this;
	}
	
	public StatisticBean setLongPassing(Integer longPassing) {
		if(longPassing == null || longPassing > 100 || longPassing < 1) {
			this.longPassing = 50;
		} else {
			this.longPassing = longPassing.intValue();
		}
		return this;
	}
	
	public StatisticBean setCurve(Integer curve) {
		if(curve == null || curve > 100 || curve < 1) {
			this.curve = 50;
		} else {
			this.curve = curve.intValue();
		}
		return this;
	}
	
	public StatisticBean setAgility(Integer agility) {
		if(agility == null || agility > 100 || agility < 1) {
			this.agility = 50;
		} else {
			this.agility = agility.intValue();
		}
		return this;
	}
	
	public StatisticBean setBalance(Integer balance) {
		if(balance == null || balance > 100 || balance < 1) {
			this.agility = 50;
		} else {
			this.agility = balance.intValue();
		}
		return this;
	}
	
	public StatisticBean setReactions(Integer reactions) {
		if(reactions == null || reactions > 100 || reactions < 1) {
			this.reactions = 50;
		} else {
			this.reactions = reactions.intValue();
		}
		return this;
	}
	
	public StatisticBean setBallControl(Integer ballControl) {
		if(ballControl == null || ballControl > 100 || ballControl < 1) {
			this.ballControl = 50;
		} else {
			this.ballControl = ballControl.intValue();
		}
		return this;
	}
	
	public StatisticBean setDribbling(Integer dribbling) {
		if(dribbling == null || dribbling > 100 || dribbling < 1) {
			this.dribbling = 50;
		} else {
			this.dribbling = dribbling.intValue();
		}
		return this;
	}
	
	public StatisticBean setComposure(Integer composure) {
		if(composure == null || composure > 100 || composure < 1) {
			this.composure = 50;
		} else {
			this.composure = composure.intValue();
		}
		return this;
	}
	
	public StatisticBean setInterceptions(Integer interceptions) {
		if(interceptions == null || interceptions > 100 || interceptions < 1) {
			this.interceptions = 50;
		} else {
			this.interceptions = interceptions.intValue();
		}
		return this;
	}
	
	public StatisticBean setHeadingAccuracy(Integer headingAccuracy) {
		if(headingAccuracy == null || headingAccuracy > 100 || headingAccuracy < 1) {
			this.headingAccuracy = 50;
		} else {
			this.headingAccuracy = headingAccuracy.intValue();
		}
		return this;
	}
	
	public StatisticBean setMarking(Integer marking) {
		if(marking == null || marking > 100 || marking < 1) {
			this.marking = 50;
		} else {
			this.marking = marking.intValue();
		}
		return this;
	}
	
	public StatisticBean setStandingTackle(Integer standingTackle) {
		if(standingTackle == null || standingTackle > 100 || standingTackle < 1) {
			this.standingTackle = 50;
		} else {
			this.standingTackle = standingTackle.intValue();
		}
		return this;
	}
	
	public StatisticBean setSlidingTackle(Integer slidingTackle) {
		if(slidingTackle == null || slidingTackle > 100 || slidingTackle < 1) {
			this.slidingTackle = 50;
		} else {
			this.slidingTackle = slidingTackle.intValue();
		}
		return this;
	}
	
	public StatisticBean setJumping(Integer jumping) {
		if(jumping == null || jumping > 100 || jumping < 1) {
			this.jumping = 50;
		} else {
			this.jumping = jumping.intValue();
		}
		return this;
	}
	
	public StatisticBean setStamina(Integer stamina) {
		if(stamina == null || stamina > 100 || stamina < 1) {
			this.stamina = 50;
		} else {
			this.stamina = stamina.intValue();
		}
		return this;
	}
	
	public StatisticBean setStrength(Integer strength) {
		if(strength == null || strength > 100 || strength < 1) {
			this.strength = 50;
		} else {
			this.strength = strength.intValue();
		}
		return this;
	}
	
	public StatisticBean setAggression(Integer aggression) {
		if(aggression == null || aggression > 100 || aggression < 1) {
			this.aggression = 50;
		} else {
			this.aggression = aggression.intValue();
		}
		return this;
	}
}
