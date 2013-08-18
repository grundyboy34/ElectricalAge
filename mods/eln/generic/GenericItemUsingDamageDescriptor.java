package mods.eln.generic;

import java.util.List;

import mods.eln.Eln;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

public class GenericItemUsingDamageDescriptor {
	public String IconName;
	private Icon iconIndex;
	public String name;
	
	public Item parentItem;
	public int parentItemDamage;
	
	public GenericItemUsingDamageDescriptor(String name) {
		this.IconName = "eln:" + name.replaceAll(" ", "") ;
		this.name = name;
	}
	public void changeDefaultIcon(String name)
	{
		this.IconName = "eln:" + name.replaceAll(" ", "") ;
	}
	public NBTTagCompound getDefaultNBT()
	{
		return null;
	}
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean par4)
	{
		
	}
	public void getSubItems(List list)
	{
		ItemStack stack = newItemStack(1);
        list.add(stack);
	}
    public void updateIcons(IconRegister iconRegister)
    {
        this.iconIndex = iconRegister.registerIcon(IconName);
    }
	
	public Icon getIcon()
	{
		return iconIndex;
	}
	
	public String getName(ItemStack stack)
	{
		return name;
	}
	
	public static GenericItemUsingDamageDescriptor getDescriptor(ItemStack stack)
	{
		if(stack == null) return null;
		if((stack.getItem() instanceof GenericItemUsingDamage) == false) return null;
		return ((GenericItemUsingDamage<GenericItemUsingDamageDescriptor>)stack.getItem()).getDescriptor(stack);
	}
	public static GenericItemUsingDamageDescriptor getDescriptor(
			ItemStack stack,
			Class extendClass) {
		GenericItemUsingDamageDescriptor desc = getDescriptor(stack);
		if(desc == null) return null;
		if(extendClass.isAssignableFrom(desc.getClass()) == false) return null;
		return desc;
	}
	public void setParent(Item item,int damage)
	{
		this.parentItem = item;
		this.parentItemDamage = damage;
	}
	public ItemStack newItemStack(int size)
	{
		ItemStack stack = new ItemStack(parentItem, size, parentItemDamage);
		stack.setTagCompound(getDefaultNBT());
		return stack;
	}
	
	public boolean checkSameItemStack(ItemStack stack)
	{
		if(stack == null) return false;
		if(stack.getItem() != parentItem || stack.getItemDamage() != parentItemDamage) return false;
		return true;
	}
	
	
	
    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float vx, float vy, float vz)
    {
        return false;
    }
    
    
    
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		// TODO Auto-generated method stub
		
	}	
	
	

	 public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	 {
		 
		 
	 }
	 
	 
		
	protected NBTTagCompound getNbt(ItemStack stack){
		NBTTagCompound nbt = stack.getTagCompound();
		if(nbt == null){
			stack.setTagCompound(nbt = getDefaultNBT());
		}
		return nbt;		
	}
	

	   public float getStrVsBlock(ItemStack stack, Block block)
	   {
		   return 1f;
	   }




	   public boolean onBlockDestroyed(ItemStack stack, World w, int blockId, int x, int y, int z, EntityLivingBase entity)
	   {
		   return false;
	   }
	   

}
