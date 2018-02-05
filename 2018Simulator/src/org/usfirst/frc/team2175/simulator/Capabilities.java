package org.usfirst.frc.team2175.simulator;

public class Capabilities
{

	// this class defines the capabilities of a robot

	double maxSpeed = 0.0; // in inches per second

	double maxAacceleration = 0.0; // in inches / sec^2

	boolean canClimb = true;

	double probabilityOfPlaceOnSwitch = 0.8;
	double averageTimeToPlaceonSwitch = 0.4;

	double probabilityOfPlaceOnScale = 0.8;
	double averageTimeToPlaceonScale = 1.3;

	double probabilityOfPlaceinExchange = 0.8;
	double averageTimeToPlaceinExchange = 0.8;

	double probabilityOfIntake = 0.9;
	double averageTimeToIntake = 0.8;

	public double getMaxSpeed()
	{
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed)
	{
		this.maxSpeed = maxSpeed;
	}

	public double getMaxAacceleration()
	{
		return maxAacceleration;
	}

	public void setMaxAacceleration(double maxAacceleration)
	{
		this.maxAacceleration = maxAacceleration;
	}

	public boolean isCanClimb()
	{
		return canClimb;
	}

	public void setCanClimb(boolean canClimb)
	{
		this.canClimb = canClimb;
	}

	public double getProbabilityOfPlaceOnSwitch()
	{
		return probabilityOfPlaceOnSwitch;
	}

	public void setProbabilityOfPlaceOnSwitch(double probabilityOfPlaceOnSwitch)
	{
		this.probabilityOfPlaceOnSwitch = probabilityOfPlaceOnSwitch;
	}

	public double getAverageTimeToPlaceonSwitch()
	{
		return averageTimeToPlaceonSwitch;
	}

	public void setAverageTimeToPlaceonSwitch(double averageTimeToPlaceonSwitch)
	{
		this.averageTimeToPlaceonSwitch = averageTimeToPlaceonSwitch;
	}

	public double getProbabilityOfPlaceOnScale()
	{
		return probabilityOfPlaceOnScale;
	}

	public void setProbabilityOfPlaceOnScale(double probabilityOfPlaceOnScale)
	{
		this.probabilityOfPlaceOnScale = probabilityOfPlaceOnScale;
	}

	public double getAverageTimeToPlaceonScale()
	{
		return averageTimeToPlaceonScale;
	}

	public void setAverageTimeToPlaceonScale(double averageTimeToPlaceonScale)
	{
		this.averageTimeToPlaceonScale = averageTimeToPlaceonScale;
	}

	public double getProbabilityOfPlaceinExchange()
	{
		return probabilityOfPlaceinExchange;
	}

	public void setProbabilityOfPlaceinExchange(double probabilityOfPlaceinExchange)
	{
		this.probabilityOfPlaceinExchange = probabilityOfPlaceinExchange;
	}

	public double getAverageTimeToPlaceinExchange()
	{
		return averageTimeToPlaceinExchange;
	}

	public void setAverageTimeToPlaceinExchange(double averageTimeToPlaceinExchange)
	{
		this.averageTimeToPlaceinExchange = averageTimeToPlaceinExchange;
	}

	public double getProbabilityOfIntake()
	{
		return probabilityOfIntake;
	}

	public void setProbabilityOfIntake(double probabilityOfIntake)
	{
		this.probabilityOfIntake = probabilityOfIntake;
	}

	public double getAverageTimeToIntake()
	{
		return averageTimeToIntake;
	}

	public void setAverageTimeToIntake(double averageTimeToIntake)
	{
		this.averageTimeToIntake = averageTimeToIntake;
	}

}
