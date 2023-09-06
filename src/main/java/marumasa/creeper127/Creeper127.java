package marumasa.creeper127;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class Creeper127 implements ModInitializer {

    // ModのID
    public static final String MOD_ID = "creeper127";

    // クリーパーのスポーンエッグ
    private static final ItemStack CreeperEgg = new ItemStack(Items.CREEPER_SPAWN_EGG);

    // クリエイティブタブ
    private static final ItemGroup CreeperTab = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.TNT))
            .displayName(Text.of("Creeper127で追加されたタブ")).entries((displayContext, entries) -> {

                // ここにクリエイティブに追加したいアイテムを書く
                entries.add(CreeperEgg);

            })
            .build();

    @Override
    public void onInitialize() {
        try {

            //---------- クリーパーのスポーンエッグの NBTタグ を設定---------- start
            // String から変換
            NbtCompound NbtTag = NbtHelper.fromNbtProviderString(
                    "{EntityTag:{id:creeper,Fuse:0,ExplosionRadius:127},Enchantments:[{}],display:{Name:'[{\"text\":\"お掃除ボム\",\"italic\":false}]',Lore:['[{\"text\":\"\",\"italic\":false}]']}}"
            );
            // スポーンエッグのNBTタグ 設定
            CreeperEgg.setNbt(NbtTag);
            //---------- クリーパーのスポーンエッグの NBTタグ を設定---------- end


            // クリエイティブタブのID を 設定
            Identifier tab_id = new Identifier(MOD_ID, "test_group");

            // クリエイティブタブ登録
            Registry.register(Registries.ITEM_GROUP, tab_id, CreeperTab);

        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
