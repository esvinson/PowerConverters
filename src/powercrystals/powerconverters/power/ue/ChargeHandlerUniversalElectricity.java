package powercrystals.powerconverters.power.ue;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import powercrystals.powerconverters.PowerConverterCore;
import powercrystals.powerconverters.common.IChargeHandler;
import powercrystals.powerconverters.power.PowerSystem;
import universalelectricity.core.implement.IItemElectric;
import universalelectricity.core.implement.IVoltage;

public class ChargeHandlerUniversalElectricity implements IChargeHandler
{
	@Override
	public PowerSystem getPowerSystem()
	{
		return PowerConverterCore.powerSystemUniversalElectricity;
	}

	@Override
	public boolean canHandle(ItemStack stack)
	{
		return stack != null && Item.itemsList[stack.itemID] != null && Item.itemsList[stack.itemID] instanceof IItemElectric;
	}

	@Override
	public int charge(ItemStack stack, int energyInput)
	{
		IItemElectric item = (IItemElectric)Item.itemsList[stack.itemID];
		if(!item.canReceiveElectricity())
		{
			return 0;
		}
		
		double voltage = ((IVoltage)item).getVoltage(stack); // is this correct? WHO KNOWS
		double watts = energyInput / PowerConverterCore.powerSystemUniversalElectricity.getInternalEnergyPerOutput();
		double amps = watts / voltage;
		
		double joulesRemaining = item.onReceive(amps, voltage, stack);
		
		int energyRemaining = MathHelper.floor_double(joulesRemaining * PowerConverterCore.powerSystemUniversalElectricity.getInternalEnergyPerOutput());
		
		return energyRemaining;
	}

	@Override
	public int discharge(ItemStack stack, int energyRequest)
	{
		return 0;
	}
}
