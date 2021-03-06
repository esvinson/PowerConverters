package powercrystals.powerconverters.power.factorization;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import powercrystals.powerconverters.PowerConverterCore;
import powercrystals.powerconverters.gui.PCCreativeTab;
import powercrystals.powerconverters.power.BlockPowerConverter;

/**
 * Created with IntelliJ IDEA.
 * User: james
 * Date: 3/03/14
 * Time: 2:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class BlockPowerConverterFactorization extends BlockPowerConverter
{
    public BlockPowerConverterFactorization(int par1)
    {
        super(par1, 2);
        setUnlocalizedName("powerconverters.factorization");
        setCreativeTab(PCCreativeTab.tab);
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata == 0) return new TileEntityPowerConverterFactorizationConsumer();
        else if (metadata == 1) return new TileEntityPowerConverterFactorizationProducer();

        return createNewTileEntity(world);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir) {
        String[] types = { "consumer", "producer" };
        String[] states = { "off", "on" };
        String folderName = getUnlocalizedName().substring("tile.powerconverters.".length());

        int i = 0;
        for(String type : types)
        {
            for(String state : states)
            {
                _icons[i] = ir.registerIcon(String.format("%s:%s/%s_%s", PowerConverterCore.modId, folderName, type, state));
                i++;
            }
        }
    }
}
