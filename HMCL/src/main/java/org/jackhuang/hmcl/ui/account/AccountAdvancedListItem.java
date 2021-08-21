/*
 * Hello Minecraft! Launcher
 * Copyright (C) 2020  huangyuhui <huanghongxun2008@126.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.jackhuang.hmcl.ui.account;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import org.jackhuang.hmcl.auth.Account;
import org.jackhuang.hmcl.auth.authlibinjector.AuthlibInjectorAccount;
import org.jackhuang.hmcl.auth.authlibinjector.AuthlibInjectorServer;
import org.jackhuang.hmcl.auth.yggdrasil.YggdrasilAccount;
import org.jackhuang.hmcl.game.TexturesLoader;
import org.jackhuang.hmcl.setting.Accounts;
import org.jackhuang.hmcl.ui.FXUtils;
import org.jackhuang.hmcl.ui.construct.AdvancedListItem;
import org.jackhuang.hmcl.util.Pair;

import static org.jackhuang.hmcl.ui.FXUtils.newImage;
import static org.jackhuang.hmcl.util.i18n.I18n.i18n;

public class AccountAdvancedListItem extends AdvancedListItem {
    private final Tooltip tooltip;
    private final ImageView imageView;

    private ObjectProperty<Account> account = new SimpleObjectProperty<Account>() {

        @Override
        protected void invalidated() {
            Account account = get();
            if (account == null) {
                titleProperty().unbind();
                setTitle(i18n("account.missing"));
                setSubtitle(i18n("account.missing.add"));
                imageView.imageProperty().unbind();
                imageView.setImage(newImage("/assets/img/steve.png"));
                tooltip.setText("");
            } else {
                titleProperty().bind(Bindings.createStringBinding(account::getCharacter, account));
                setSubtitle(accountSubtitle(account));
                imageView.imageProperty().bind(TexturesLoader.fxAvatarBinding(account, 42));
                tooltip.setText(account.getCharacter() + " " + accountTooltip(account));
            }
        }
    };

    public AccountAdvancedListItem() {
        tooltip = new Tooltip();
        FXUtils.installFastTooltip(this, tooltip);

        Pair<Node, ImageView> view = createImageView(null, 42, 42);
        setLeftGraphic(view.getKey());
        imageView = view.getValue();

        setOnScroll(event -> {
            Account current = account.get();
            if (current == null) return;
            ObservableList<Account> accounts = Accounts.getAccounts();
            int currentIndex = accounts.indexOf(account.get());
            if (event.getDeltaY() > 0) { // up
                currentIndex--;
            } else { // down
                currentIndex++;
            }
            Accounts.setSelectedAccount(accounts.get((currentIndex + accounts.size()) % accounts.size()));
        });
    }

    public ObjectProperty<Account> accountProperty() {
        return account;
    }

    private static String accountSubtitle(Account account) {
        String loginTypeName = Accounts.getLocalizedLoginTypeName(Accounts.getAccountFactory(account));
        if (account instanceof AuthlibInjectorAccount) {
            return ((AuthlibInjectorAccount) account).getServer().getName();
        } else {
            return loginTypeName;
        }
    }

    private static String accountTooltip(Account account) {
        if (account instanceof AuthlibInjectorAccount) {
            AuthlibInjectorServer server = ((AuthlibInjectorAccount) account).getServer();
            return account.getUsername() + ", " + i18n("account.injector.server") + ": " + server.getName();
        } else if (account instanceof YggdrasilAccount) {
            return account.getUsername();
        } else {
            return "";
        }
    }

//    private static class AccountAdvancedListItemSkin extends SkinBase<AccountAdvancedListItemSkin> {
//        private final PseudoClass SELECTED = PseudoClass.getPseudoClass("selected");
//
//        public AccountAdvancedListItemSkin(AccountAdvancedListItemSkin skinnable) {
//            super(skinnable);
//
//            FXUtils.onChangeAndOperate(skinnable.activeProperty(), active -> {
//                skinnable.pseudoClassStateChanged(SELECTED, active);
//            });
//
//            BorderPane root = new BorderPane();
//            root.getStyleClass().add("container");
//            root.setPickOnBounds(false);
//
//            RipplerContainer container = new RipplerContainer(root);
//
//            HBox left = new HBox();
//            left.setAlignment(Pos.CENTER_LEFT);
//            left.setMouseTransparent(true);
//
//            TwoLineListItem item = new TwoLineListItem();
//            root.setCenter(item);
//            item.setMouseTransparent(true);
//            item.titleProperty().bind(skinnable.titleProperty());
//            item.subtitleProperty().bind(skinnable.subtitleProperty());
//
//            FXUtils.onChangeAndOperate(skinnable.leftGraphicProperty(),
//                    newGraphic -> {
//                        if (newGraphic == null) {
//                            left.getChildren().clear();
//                        } else {
//                            left.getChildren().setAll(newGraphic);
//                        }
//                    });
//            root.setLeft(left);
//
//            HBox right = new HBox();
//            right.setAlignment(Pos.CENTER);
//            right.setMouseTransparent(true);
//            right.getStyleClass().add("toggle-icon4");
//            FXUtils.setLimitWidth(right, 40);
//            FXUtils.onChangeAndOperate(skinnable.rightGraphicProperty(),
//                    newGraphic -> {
//                        if (newGraphic == null) {
//                            right.getChildren().clear();
//                        } else {
//                            right.getChildren().setAll(newGraphic);
//                        }
//                    });
//
//            FXUtils.onChangeAndOperate(skinnable.actionButtonVisibleProperty(),
//                    visible -> root.setRight(visible ? right : null));
//
//            getChildren().setAll(container);
//        }
//    }
}
