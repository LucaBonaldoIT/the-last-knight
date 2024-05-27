package main.java.com.mealforks.thelastknight.game;

public class GamePlayerAttributes {
    private int _strength;
    private int _endurance;
    private int _luck;
    private int _intelligence;
    private int _agility;

    public GamePlayerAttributes()
    {
        _strength = 0;
        _endurance = 0;
        _luck = 0;
        _intelligence = 0;
        _agility = 0;
    }

    // Todo: COMPLETE METHODS

    public int getEndurance()
    {
        return _endurance;
    }

    public int getAgility()
    {
        return _agility;
    }

    public void setAgility(int agility)
    {
        _agility = agility;
    }

    public int getStrength() {
        return _strength;
    }

    public void setStrength(int strength)
    {
        _strength = strength;
    }

    public void onNewLevel(GamePlayerClass playerClass)
    {
        switch (playerClass)
        {
            case KNIGHT:
            {
                _strength += 3;
                _endurance += 2;
                _intelligence += 1;
                _agility += 1;
                break;
            }
            case MAGE:
            {
                _strength += 1;
                _endurance += 1;
                _intelligence += 4;
                _luck += 1;
                break;
            }
            case THIEF:
            {
                _agility += 3;
                _endurance += 2;
                _strength += 1;
                _intelligence += 1;
                break;
            }
        }
    }
}
